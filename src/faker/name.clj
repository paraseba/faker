(ns faker.name
  (:use 
     (clojure.contrib [seq :only (rand-elt)]
                      [string :only (join)])
     faker.name-data))

(defn rand-first []
  (rand-elt first-names))

(defn rand-last []
  (rand-elt last-names))

(defn rand-pre []
  (rand-elt prefixes))

(defn rand-suf []
  (rand-elt suffixes))

(defn- comb [& funs]
  (fn [] (join " " (map #(%) funs))))

(def format-probs
  [[(comb rand-pre rand-first rand-last) 0.1]
   [(comb rand-first rand-last rand-suf) 0.2]
   [(comb rand-first rand-last) 1]])

(defn gen-name
  []
  (some #(and (> (last %) (rand)) ((first %))) format-probs))
