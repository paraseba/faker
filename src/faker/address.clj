(ns faker.address
  (:use
     (clojure.contrib [string :only (upper-case replace-by)]
                      [def :only (defvar-)])
     faker.address-data)
  (:require [faker.name :as na]))


(defn- numerify [& formats]
  (replace-by #"#"
              (fn [_] (str (rand-int 10)))
              (rand-nth formats)))


(defn zip-code []
  (numerify "#####" "#####-####"))

(defn us-state [] (rand-nth us-states))
(defn us-state-abbr [] (rand-nth us-state-abbrs))
(defn city-prefix [] (rand-nth city-prefixes))
(defn city-suffix [] (rand-nth city-suffixes))
(defn street-suffix [] (rand-nth street-suffixes))

(defvar- city-formats
  [#(format "%s %s%s" (city-prefix) (na/first-name) (city-suffix))
   #(format "%s %s" (city-prefix) (na/first-name))
   #(format "%s%s" (na/first-name) (city-suffix))
   #(format "%s%s" (na/last-name) (city-suffix))])
      
(defn city []
  ((rand-nth city-formats)))

      
(defvar- street-formats
  [#(format "%s %s" (na/last-name) (street-suffix))
   #(format "%s %s" (na/first-name) (street-suffix))])

(defn street-name []
  ((rand-nth street-formats)))
    
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

(defn uk-county [] (rand-nth uk-counties))
(defn uk-country [] (rand-nth uk-countries))

(defvar- alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(defn- letterify [& formats]
  (replace-by #"\?"
              (fn [_] (str (rand-nth alphabet)))
              (rand-nth formats)))

(defn uk-postcode []
  (upper-case (letterify (numerify "??# #??" "??## #??"))))

