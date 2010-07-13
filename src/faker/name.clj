(ns faker.name
  (:use 
     (clojure.contrib
       [string :only (join)]
       [def :only (defvar-)])     
     faker.name-data
     faker.repeatable))

(defn first-name []
  (repeatable-rand-nth first-names))

(defn last-name []
  (repeatable-rand-nth last-names))

(defn prefix []
  (repeatable-rand-nth prefixes))

(defn suffix []
  (repeatable-rand-nth suffixes))

(defn- comb [& funs]
  (fn [] (join " " (map #(%) funs))))

(defvar- format-probs
  [[(comb prefix first-name last-name) 0.1]
   [(comb first-name last-name suffix) 0.2]
   [(comb first-name last-name) 1]])

(defn- one-name []
  (let [p (repeatable-rand)]
    (some #(and (> (last %) p)
                ((first %)))
          format-probs)))

(defn names
  []
  (repeatedly one-name))
