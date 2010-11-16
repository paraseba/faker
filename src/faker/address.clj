(ns faker.address
  "Create fake address data."
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


(defn zip-code
  "Create a random USA zip code."
  []
  (numerify "#####" "#####-####"))

(defn us-state
  "Returns a random USA state."
  []
  (repeatable-rand-nth us-states))

(defn us-state-abbr
  "Returns a random USA state abbreviation."
  []
  (repeatable-rand-nth us-state-abbrs))

(defn city-prefix
  "Returns a random city prefix, like North or South."
  []
  (repeatable-rand-nth city-prefixes))

(defn city-suffix
  "Returns a random city suffix, like town or land."
  []
  (repeatable-rand-nth city-suffixes))

(defn street-suffix
  "Returns a random street suffix, like Avenue or Bridge."
  []
  (repeatable-rand-nth street-suffixes))

(defvar- city-formats
  [#(format "%s %s%s" (city-prefix) (na/first-name) (city-suffix))
   #(format "%s %s" (city-prefix) (na/first-name))
   #(format "%s%s" (na/first-name) (city-suffix))
   #(format "%s%s" (na/last-name) (city-suffix))])
      
(defn city []
  "Returns a random city name."
  ((repeatable-rand-nth city-formats)))

      
(defvar- street-formats
  [#(format "%s %s" (na/last-name) (street-suffix))
   #(format "%s %s" (na/first-name) (street-suffix))])

(defn street-name []
  "Returns a random street name."
  ((repeatable-rand-nth street-formats)))
    
(defn secondary-address []
  "Returns a random secondary part of an address."
  (numerify "Apt. ###" "Suite ###"))

(defn street-address
  "Returns a random address.
  
  If include-secondary? is true the address will include a secondary part."
  ([] (street-address false))
  ([include-secondary?]
   (let [base (str (numerify "#####" "####" "###") " " (street-name))]
     (if include-secondary?
       (str base " " (secondary-address))
       base))))
      
; UK Variants

(defn uk-county
  "Return a random UK county."
  []
  (repeatable-rand-nth uk-counties))

(defn uk-country
  "Return a random UK country."
  []
  (repeatable-rand-nth uk-countries))

(defvar- alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(defn- letterify [& formats]
  (replace-by #"\?"
              (fn [_] (str (repeatable-rand-nth alphabet)))
              (repeatable-rand-nth formats)))

(defn uk-postcode
  "Return a random UK postcode."
  []
  (upper-case (letterify (numerify "??# #??" "??## #??"))))

