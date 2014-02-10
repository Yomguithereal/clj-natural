;; -------------------------------------------------------------------
;; clj-natural Sentence Tokenizer Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.tokenizers.sentences-test
  (:require [clojure.test :refer :all]
            [clj-natural.tokenizers.sentences :refer :all]))

(deftest tokenize-test
  (is (= ["This is what you get."
          "You crossed Georges W. Bush."
          "You lousy thing (and such)."
          "Ok?"]
         (tokenize "This is what you get. You crossed Georges W. Bush. You lousy thing (and such). Ok?"))))
