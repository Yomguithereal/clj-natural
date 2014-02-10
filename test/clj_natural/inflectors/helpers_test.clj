;; -------------------------------------------------------------------
;; clj-natural Inflector Helpers Test
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.inflectors.helpers-test
  (:require [clojure.test :refer :all]
            [clj-natural.inflectors.helpers :refer :all]))

(deftest case-type-test
  (is (= :uppercase (case-type "TEST")))
  (is (= :lowercase (case-type "test")))
  (is (= :capitalized (case-type "Test")))
  (is (= :lowercase (case-type "tEsT"))))
