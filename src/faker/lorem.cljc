(ns faker.lorem
  "Create fake textual data"
  (:require [clojure.string :refer [join capitalize]]
            [faker.lorem-data :as ld]))

(defn words
  "Lazy sequence of random latin words"
  []
  (repeatedly #(rand-nth ld/latin-words)))

(defn sentences
  "Lazy sequence of random latin sentences.

  (sentences 5) will generate a sequence of random sentences between
  5 and 5 + 5 words.
  (sentences) will generate random sentences between 4 and 4 + 5 words."
  ([] (sentences 4))
  ([word-count]
   (map
     (fn [n]
       (str (capitalize (join " " (take n (words)))) "."))
     (repeatedly #(+ word-count (rand-int 6))))))

(defn paragraphs
  "Lazy sequence of random latin paragraphs.

  (paragraphs 5) will generate a sequence of random paragraphs between
  5 and 5 + 2 sentences.
  (paragraphs) will generate random paragraphs between 3 and 3 + 2 sentences"
  ([] (paragraphs 3))
  ([sentence-count]
   (map
     (fn [n]
       (join " " (take n (sentences))))
     (repeatedly #(+ sentence-count (rand-int 3))))))

