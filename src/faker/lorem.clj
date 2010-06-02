(ns faker.lorem
  (:use 
     [clojure.contrib.string :only (join capitalize)]
     faker.lorem-data))

(defn words []
  (repeatedly #(rand-nth latin-words)))

(defn sentences
  ([] (sentences 4))
  ([word-count]
   (map
     (fn [n]
       (str (capitalize (join " " (take n (words)))) "."))
     (repeatedly #(+ word-count (rand-int 6))))))

(defn paragraphs
  ([] (paragraphs 3))
  ([sentence-count]
   (map
     (fn [n]
       (join " " (take n (sentences))))
     (repeatedly #(+ sentence-count (rand-int 3))))))

