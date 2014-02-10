;; -------------------------------------------------------------------
;; clj-natural Noun Inflector
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.inflectors.noun
  (:require [clojure.string]
            [clj-natural.inflectors.helpers :as helpers])
  (:use [clj-fuzzy.helpers :only [in? re-test?]]))

;; Rules
(def ^:private ambiguous-cases
  '("bison" "bream" "carp" "chassis" "cod" "corps"
    "debris" "deer" "diabetes" "equipment" "elk" "fish"
     "flounder" "gallows" "graffiti" "headquarters" "herpes"
     "highjinks" "homework" "information" "mackerel"
     "mews" "money" "news" "rice" "rabies" "salmon"
     "series" "sheep" "shrimp" "species" "squid" "swine" "trout"
     "tuna" "whiting" "wildebeest"))

(def ^:private irregular-cases
  '("child" "children"
    "sex" "sexes"
    "mouse" "mice"
    "ox" "oxen"
    "foot" "feet"
    "tooth" "teeth"
    "goose" "geese"))

(def ^:private irregular-cases-map
  {:pluralize (partition 2 irregular-cases)
   :singularize (partition 2 (reverse irregular-cases))})

(def ^:private rules
  {:pluralize   '(#"y$" "ies"
                  #"ife$" "ives"
                  #"(antenn|formul|nebul|vertebr|vit)a$" "$1ae"
                  #"(octop|vir|radi|nucle|fung|cact|stimul)us$" "$1i"
                  #"(buffal|tomat)o$" "$1oes"
                  #"(sis)$" "ses"
                  #"(matr|vert|ind)(ix|ex)$" "$1ices"
                  #"(x|ch|ss|sh|s|z)$" "$1es"
                  #"^(?!talis|.*hu)(.*)man$" "$1men"
                  #"(.*)" "$1s")
   :singularize '(#"([^v])ies$" "$1y"
                  #"ives$" "ife"
                  #"(antenn|formul|nebul|vertebr|vit)ae$" "$1a"
                  #"(octop|vir|radi|nucle|fung|cact|stimul)(i)$" "$1us"
                  #"(buffal|tomat)(oes)$" "$1o"
                  #"(analy|naly|synop|parenthe|diagno|the)ses$" "$1sis"
                  #"(vert|ind)(ices)$" "$1ex"
                  #"(matr|append)(ices)$" "$1ix"
                  #"(x|ch|ss|sh|s|z)es$" "$1"
                  #"men$" "man"
                  #"s$" "")})

;; Utilities
(defn- ambiguous?
  "Check wether the [word] is ambigous."
  [word]
  (in? word ambiguous-cases))

(defn- find-relevant-irregularity [word irregularities]
  (some #(when (= word (first %)) %) irregularities))

(defn- apply-irregularity
  "Apply an irregular inflection on a [word]."
  [word inflection]
  (let [found-irregularity (find-relevant-irregularity word (irregular-cases-map inflection))]
    (if found-irregularity
      (clojure.string/replace word
                              (re-pattern (str "" (first found-irregularity)))
                              (second found-irregularity))
      false)))

(defn- get-first-applying-rule [word inflection]
  (some #(when (re-test? (first %) word) %) (partition 2 (rules inflection))))

(defn- ize
  "Apply inflection on a [word]."
  [word inflection]
  (if (ambiguous? word)
    word
    (if-let [irregularity (apply-irregularity word inflection)]
      irregularity
      (if-let [rule (get-first-applying-rule word inflection)]
        (clojure.string/replace-first word (first rule) (second rule))
        word))))

(defn- inflect [word inflection]
  (let [ct (helpers/case-type word)
        ized-word (ize (clojure.string/lower-case word) inflection)]
    (helpers/apply-case ized-word ct)))

;; Main Functions
(defn singularize
  "Singularize given [word]."
  [word]
  (inflect word :singularize))

(defn pluralize
  "Pluralize given [word]."
  [word]
  (inflect word :pluralize))
