(ns faker.company
  "Create fake company data"
  (:use
     [clojure.contrib.string :only (join)]
     [clojure.contrib.def :only (defvar-)]
     faker.company-data)
  (:require [faker.name :as fkname]))

(defn suffix
  "Return a random company suffix, like Inc or Group." 
  []
  (rand-nth suffixes))

(defn- phrase [source]
  (join " " (map #(rand-nth %) source)))

(defn catch-phrase
  "Return a random company catch phrase."
  []
  (phrase catch-phrase-words))

(defn bs
  "Return random company BS goals."
  []
  (phrase bs-words))

(defvar- formats
  [#(str (first (fkname/names)) " " (suffix))
   #(str (fkname/last-name) "-" (fkname/last-name))
   #(format "%s, %s and %s" (fkname/last-name) (fkname/last-name) (fkname/last-name))])


(defn names []
  "Lazy sequence of random company names"
  (repeatedly
    (fn []
      ((rand-nth formats)))))
