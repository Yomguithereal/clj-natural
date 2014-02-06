;; -------------------------------------------------------------------
;; clj-natural Treebank Tokenizer Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.tokenizers.treebank-test
  (:require [clojure.test :refer :all]
            [clj-natural.tokenizers.treebank :refer :all]))

(deftest tokenize-test
  (is (= '("This" "is" "n't" "over" ".") (tokenize "This isn't over."))))
