(ns faker.address
  (:use
     (clojure.contrib [string :only (upper-case replace-by)]
                      [def :only (defvar-)])
     faker.address-data
     faker.repeatable)
  (:require [faker.name :as na]))


(defn- numerify [& formats]
  (replace-by #"#"
              (fn [_] (str (rand-int 10)))
              (repeatable-rand-nth formats)))


(defn zip-code []
  (numerify "#####" "#####-####"))

(defn us-state [] (repeatable-rand-nth us-states))
(defn us-state-abbr [] (repeatable-rand-nth us-state-abbrs))
(defn city-prefix [] (repeatable-rand-nth city-prefixes))
(defn city-suffix [] (repeatable-rand-nth city-suffixes))
(defn street-suffix [] (repeatable-rand-nth street-suffixes))

(defvar- city-formats
  [#(format "%s %s%s" (city-prefix) (na/first-name) (city-suffix))
   #(format "%s %s" (city-prefix) (na/first-name))
   #(format "%s%s" (na/first-name) (city-suffix))
   #(format "%s%s" (na/last-name) (city-suffix))])
      
(defn city []
  ((repeatable-rand-nth city-formats)))

      
(defvar- street-formats
  [#(format "%s %s" (na/last-name) (street-suffix))
   #(format "%s %s" (na/first-name) (street-suffix))])

(defn street-name []
  ((repeatable-rand-nth street-formats)))
    
(defn secondary-address []
  (numerify "Apt. ###" "Suite ###"))

(defn street-address
  ([] (street-address false))
  ([include-secondary?]
   (let [base (str (numerify "#####" "####" "###") " " (street-name))]
     (if include-secondary?
       (str base " " (secondary-address))
       base))))
      
; UK Variants

(defn uk-county [] (repeatable-rand-nth uk-counties))
(defn uk-country [] (repeatable-rand-nth uk-countries))

(defvar- alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(defn- letterify [& formats]
  (replace-by #"\?"
              (fn [_] (str (repeatable-rand-nth alphabet)))
              (repeatable-rand-nth formats)))

(defn uk-postcode []
  (upper-case (letterify (numerify "??# #??" "??## #??"))))

