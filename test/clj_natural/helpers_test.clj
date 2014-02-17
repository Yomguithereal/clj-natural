;; -------------------------------------------------------------------
;; clj-natural Main Helpers Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.helpers-test
  (:require [clojure.test :refer :all]
            [clj-natural.helpers :refer :all]))

(deftest replace-accents-test
  (is (= "mange" (replace-accents "mangé")))
  (is (= "test" (replace-accents "test"))))
