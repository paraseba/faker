(ns faker.core-test
  (:use [faker.name] :reload-all)
  (:use [clojure.test]
     [clojure.contrib.string :only (split)]))

(deftest test-name-generation
  (is (gen-name))
  (let [many (take 10000 (repeatedly #(split #" " (gen-name))))
        count-simple (count (filter #(= 2 (count %)) many))]
    (is (and (> count-simple 7000) (< count-simple 9000)))))
