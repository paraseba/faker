(ns faker.lorem
  (:use 
     [clojure.contrib.string :only (join capitalize)]
     faker.lorem-data
     faker.repeatable))

(defn words []
  (repeatedly #(repeatable-rand-nth latin-words)))

(defn sentences
  ([] (sentences 4))
  ([word-count]
   (map
     (fn [n]
       (str (capitalize (join " " (take n (words)))) "."))
     (repeatedly #(+ word-count (repeatable-rand-int 6))))))

(defn paragraphs
  ([] (paragraphs 3))
  ([sentence-count]
   (map
     (fn [n]
       (join " " (take n (sentences))))
     (repeatedly #(+ sentence-count (repeatable-rand-int 3))))))

