(ns faker.company
  (:use
     [clojure.contrib.string :only (join)]
     [clojure.contrib.def :only (defvar-)]
     faker.company-data)
  (:require [faker.name :as fkname]))

(defn suffix []
  (rand-nth suffixes))

(defn- phrase [source]
  (join " " (map #(rand-nth %) source)))

(defn catch-phrase []
  (phrase catch-phrase-words))

(defn bs []
  (phrase bs-words))

(defvar- formats
  [#(str (first (fkname/names)) " " (suffix))
   #(str (fkname/last-name) "-" (fkname/last-name))
   #(format "%s, %s and %s" (fkname/last-name) (fkname/last-name) (fkname/last-name))])


(defn names []
  (repeatedly
    (fn []
      ((rand-nth formats)))))
