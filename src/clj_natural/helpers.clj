;; -------------------------------------------------------------------
;; clj-natural Main Helpers
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-natural.helpers)

(def ^:private accentuation-rules
  (zipmap "ÁÀÂÄÃÅÇÉÈÊËÍÏÎÌÑÓÒÔÖÕÚÙÛÜÝáàâäãåçéèêëíìîïñóòôöõúùûüýÿ"
          "AAAAAACEEEEEIIINOOOOOUUUUYaaaaaaceeeeiiiinooooouuuuyy"))

(defn replace-accents
  "Replace every accents in a [string] by its ASCII counterparts."
  [string]
  (apply str (map #(get accentuation-rules % %) string)))
