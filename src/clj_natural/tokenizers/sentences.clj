;; -------------------------------------------------------------------
;; clj-natural Naive Sentences Tokenizer
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.tokenizers.sentences
  (:require clojure.string))

(def ^:private pattern
  (re-pattern
    (str "(?<=[a-z0-9\"'\\)\\]][.?!]{1,3})"
         "(?<!Mr\\.|Mrs\\.|Ms\\.|Mme\\.|Mlle\\.|Jr\\.|Dr\\.|Prof\\.|Sr.\\|Mgr\\.|etc\\.)"
           "(\\s|\\r\\n)"
         "(?=\"?[A-Z])")))

(defn tokenize
  "Tokenize a string into sentences. The regex pattern used here
   is really naive but will work on almost every well formatted
   sentence."
  [string]
  (clojure.string/split string pattern))
