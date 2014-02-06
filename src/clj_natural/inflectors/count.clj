;; -------------------------------------------------------------------
;; clj-natural Count Inflector
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.inflectors.count)

(defn- get-nth
  "Get the number's suffix."
  [number]
  (let [teenth (mod number 100)]
    (if (and (> teenth 10)
             (< teenth 14))
      "th"
      (case (mod number 10)
        1 "st"
        2 "nd"
        3 "rd"
        "th"))))

(defn add-nth
  "Take a number and add the correct nth prefix."
  [number]
  (let [i (if (integer? number) number (Integer/parseInt number))]
    (str number (get-nth i))))
