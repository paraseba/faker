(ns faker.commerce-test
  (:require [faker.commerce :as com]
            [clojure.test :refer [deftest is]]))

(deftest product
  (is (com/product-name))
  (is (com/product))
  (is (com/material))
  (is (com/adjective)))

(deftest color
  (is (com/color)))

(deftest department
  (is (com/department)))

(deftest price
  (is (com/price))
  (is (com/price 10))
  (is (com/price 5 10))
  (is (thrown? AssertionError (com/price 10 5))))

