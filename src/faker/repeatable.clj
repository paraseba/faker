(ns faker.repeatable)

(def rand-generator (new java.util.Random))

(defn reseed [seed]
  (. rand-generator setSeed seed))

(defn repeatable-rand-int [n]
  (. rand-generator nextInt n))

(defn repeatable-rand []
  (. rand-generator nextDouble))

(defn repeatable-rand-nth [coll]
  (nth coll (int (* (count coll) (repeatable-rand)))))