;; -------------------------------------------------------------------
;; clj-natural Treebank Tokenizer
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.tokenizers.treebank
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [batch-replace]]))

;; Contraction rules
(defn- make-contraction-rules [rules replacement]
  (interleave rules (repeat (count rules) replacement)))

(def ^:private contractions-1
  (make-contraction-rules
   '(#"(?i)\b(can)(not)\b"
     #"(?i)\b(d)('ye)\b"
     #"(?i)\b(gim)(me)\b"
     #"(?i)\b(gon)(na)\b"
     #"(?i)\b(got)(ta)\b"
     #"(?i)\b(lem)(me)\b"
     #"(?i)\b(mor)('n)\b"
     #"(?i)\b(wan)(na) ")
   " $1 $2 "))

(def ^:private contractions-2
  (make-contraction-rules
   '(#"(?i) ('t)(is)\b"
     #"(?i) ('t)(was)\b")
   " $1 $2 "))

(def ^:private contractions-3
  (make-contraction-rules
   '(#"(?i)\b(whad)(dd)(ya)\b"
     #"(?i)\b(wha)(t)(cha)\b")
   " $1 $2 $3 "))

;; Standard rules
(def ^:private rules-1
  '(#"^\"" "``"
    #"(``)" " $1 "
    #"([ (\[{<])\"" "$1 `` "
    #"([:,])([^\d])" " $1 $2"
    #"\.\.\." " ... "
    #"([;@#$%&])" " $1 "
    #"([^\.])(\.)([\]\)}>\"']*)\s*$" "$1 $2$3 "
    #"([?!])" " $1 "
    #"([^'])' " "$1 ' "
    #"([\]\[\(\)\{\}\<\>])" " $1 "
    #"--" " -- "))

(def ^:private rules-2
  '(#"\"" " '' "
    #"(\S)('')" "$1 $2 "
    #"([^' ])('[sS]|'[mM]|'[dD]|') " "$1 $2 "
    #"([^' ])('ll|'LL|'re|'RE|'ve|'VE|n't|N'T) " "$1 $2 "))

;; Main functions
(defn- apply-rules [string]
  (batch-replace (str " " (batch-replace string rules-1) " ")
                 rules-2))

(defn- apply-contractions [string]
  (batch-replace string (concat contractions-1
                                contractions-2
                                contractions-3)))
;;TODO: Make this Lazy
(defn tokenize
  "Tokenize a [string] following the Treebank rules."
  [string]
  (let [prepared-string (-> (apply-rules string)
                            (apply-contractions)
                            (clojure.string/split #" "))]
    (map clojure.string/trim
         (remove empty? prepared-string))))
