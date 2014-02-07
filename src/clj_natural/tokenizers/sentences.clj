;; -------------------------------------------------------------------
;; clj-natural Naive Sentences Tokenizer
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.tokenizers.sentences++
  (:require clojure.string))

(def ^:private pattern
  (re-pattern
    (str "((?<=[a-z0-9][.?!])|(?<=[a-z0-9][.?!]\"))"
         "(?<!M\\.|Mr\\.|Mrs\\.|Ms\\.|Mme\\.|Mlle\\.|Jr\\.|Dr\\.|Prof\\.|Sr.\\|Mgr\\.)"
           "(\\s|\\r\\n)"
         "(?=\"?[A-Z])?")))

(defn tokenize
  "Tokenize a string into sentences. The regex pattern used here
   is really naive but will work on almost 95% of well formatted
   sentences."
  [string]
  (clojure.string/split string pattern))
