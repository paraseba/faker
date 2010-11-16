(ns faker.internet
  "Generate fake domains and emails."
  (:use
     [clojure.contrib.def :only (defvar-)]
     [clojure.contrib.string :only (join split replace-re lower-case)]
     faker.repeatable)
  (:require
     [faker.company :as co]
     [faker.name :as na]))

(defvar- suffixes ["co.uk" "com" "us" "uk" "ca" "biz" "info" "name"])

(defn domain-suffix
  "Return a random domain suffix, like com or ca"
  []
  (repeatable-rand-nth suffixes))

(defn domain-word
  "Return a random domain name, like a main company name."
  []
  (->> (first (co/names))
       (split #" ")
       first
       (replace-re #"\W" "")
       lower-case))

(defn domain-name
  "Return a random domain name."
  []
  (str (domain-word) "." (domain-suffix)))

(defn- clean [s]
  (replace-re #"\W" "" s))

(defvar- formats
  [#(lower-case (clean (na/first-name)))
   #(lower-case (join (repeatable-rand-nth ["." "-"])
                      [(clean (na/first-name))
                       (clean (na/last-name))]))])

(defn user-name
  "Return a random user name for email address."
  ([name]
   (->> name
        (split #"\W+")
        shuffle
        (join (repeatable-rand-nth ["." "-"]))
        lower-case))

  ([] ((repeatable-rand-nth formats))))

(defn email
  "Return a random email address."
  ([] (str (user-name) "@" (domain-name)))
  ([name] (str (user-name name) "@" (domain-name))))

(defvar- free-domains
  ["gmail.com" "yahoo.com" "hotmail.com"])

(defn free-email
  "Return a random free email address."
  ([] (str (user-name) "@" (repeatable-rand-nth free-domains)))
  ([name] (str (user-name name) "@" (rand-nth free-domains))))

(defn emails
  "Lazy sequence of random emails."
  []
  (repeatedly
    (fn []
      ((repeatable-rand-nth [free-email email])))))
