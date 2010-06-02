(ns faker.name
  (:use 
     (clojure.contrib
       [string :only (join)]
       [def :only (defvar-)])
     faker.name-data))

(defn first-name []
  (rand-nth first-names))

(defn last-name []
  (rand-nth last-names))

(defn prefix []
  (rand-nth prefixes))

(defn suffix []
  (rand-nth suffixes))

(defn- comb [& funs]
  (fn [] (join " " (map #(%) funs))))

(defvar- format-probs
  [[(comb prefix first-name last-name) 0.1]
   [(comb first-name last-name suffix) 0.2]
   [(comb first-name last-name) 1]])

(defn- one-name []
  (let [p (rand)]
    (some #(and (> (last %) p)
                ((first %)))
          format-probs)))

(defn names
  []
  (repeatedly one-name))
