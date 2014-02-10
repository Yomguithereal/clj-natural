;; -------------------------------------------------------------------
;; clj-natural Inflector Helpers
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.inflectors.helpers
  (:require clojure.string))

;; N.B.: For optimization reasons, this function is volontarily simplistic
(defn case-type
  "Find whether a [string] is lowercase, uppercase, capitalized
   or mixed."
  [string]
  (cond (every? #(Character/isUpperCase %) string) :uppercase
        (Character/isUpperCase (first string)) :capitalized
        :else :lowercase))

(defn apply-case
  "Mirroring the case-type function, this function apply a precise
   case on a word."
  [word ct]
  (cond (= ct :uppercase) (clojure.string/upper-case word)
        (= ct :capitalized) (clojure.string/capitalize word)
        :else (clojure.string/lower-case word)))
