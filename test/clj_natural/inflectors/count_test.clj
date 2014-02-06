;; -------------------------------------------------------------------
;; clj-natural Count Inflector Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.inflectors.count-test
  (:require [clojure.test :refer :all]
            [clj-natural.inflectors.count :refer :all]))

(deftest add-nth-test
  (is (= "1st" (add-nth 1)))
  (is (= "2nd" (add-nth 2)))
  (is (= "3rd" (add-nth 3)))
  (is (= "4th" (add-nth 4)))
  (is (= "5th" (add-nth 5)))
  (is (= "12th" (add-nth 12)))
  (is (= "14th" (add-nth 14)))
  (is (= "30th" (add-nth 30)))
  (is (= "23rd" (add-nth "23"))))
