(ns faker.commerce
  (:require [faker.commerce-data :as d]))


(defn product-name
  []
  (let [adj (rand-nth (:adjective d/product-name))
        material (rand-nth (:material d/product-name))
        product (rand-nth (:product d/product-name))]
    (str adj " " material " " product)))

(defn price
  ([]
   (price 100))
  ([upper-bound]
   (price 0 upper-bound))
  ([lower-bound upper-bound]
   {:pre (> upper-bound lower-bound)}
   (let [diff (- upper-bound lower-bound)
         upper-with-dec (* 100 diff)
         picked (rand-int upper-with-dec)
         normalized (/ (float picked) 100)
         shifted (+ normalized lower-bound)
         formatted (format "%.2f" shifted)
         numberized (read-string formatted)]
     numberized)))

(defn department
  []
  (rand-nth d/department))

(defn material
  []
  (rand-nth (:material d/product-name)))

(defn adjective
  []
  (rand-nth (:adjective d/product-name)))

(defn product
  []
  (rand-nth (:product d/product-name)))

(defn color
  []
  (rand-nth d/color))
