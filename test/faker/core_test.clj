(ns faker.core-test
  (:use (faker name lorem phone-number internet address))
  (:require [faker.company :as company])
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

(deftest test-company
  (is (company/suffix))
  (is (company/catch-phrase))
  (is (company/bs))
  (is (take 10 (company/names))))

(deftest test-internet
  (is (domain-suffix))
  (is (domain-name))
  (is (domain-word))
  (is (user-name))
  (is (email))
  (is (free-email))
  (is (take 10 emails)))

(deftest test-address
  (is (zip-code))
  (is (us-state))
  (is (us-state-abbr))
  (is (city-prefix))
  (is (city-suffix))
  (is (street-suffix))
  (is (city))
  (is (street-name))
  (is (secondary-address))
  (is (street-address))
  (is (uk-county))
  (is (uk-country))
  (is (uk-postcode)))
