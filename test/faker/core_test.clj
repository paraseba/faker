(ns faker.core-test
  (:use (faker name lorem phone-number))
  (:use
     clojure.test
     [clojure.contrib.string :only (split)]))

(deftest test-name-generation
  (is (names))
  (is (first-name))
  (is (last-name))
  (is (prefix))
  (is (suffix))
  (let [many (map #(split #" " %) (take 10000 (names)))
        count-simple (count (filter #(= 2 (count %)) many))]
    (is (and (> count-simple 7000) (< count-simple 9000)))))

(deftest test-lorem-generation
  (is (take 10 (words)))
  (is (take 10 (sentences)))
  (is (take 10 (paragraphs))))

(deftest test-phone-numbers
  (is (take 10 (phone-numbers))))
