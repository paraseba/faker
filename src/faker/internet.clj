(ns faker.internet
  (:use
     [clojure.contrib.def :only (defvar-)]
     [clojure.contrib.string :only (join split replace-re lower-case)])
  (:require
     [faker.company :as co]
     [faker.name :as na]))

(defvar- suffixes ["co.uk" "com" "us" "uk" "ca" "biz" "info" "name"])

(defn domain-suffix []
  (rand-nth suffixes))

(defn domain-word []
  (->> (first (co/names))
       (split #" ")
       first
       (replace-re #"\W" "")
       lower-case))

(defn domain-name []
  (str (domain-word) "." (domain-suffix)))

(defn- clean [s]
  (replace-re #"\W" "" s))

(defvar- formats
  [#(lower-case (clean (na/first-name)))
   #(lower-case (join (rand-nth ["." "-"])
                      [(clean (na/first-name))
                       (clean (na/last-name))]))])

(defn user-name
  ([name]
   (->> name
        (split #"\W+")
        shuffle
        (join (rand-nth ["." "-"]))
        lower-case))

  ([] ((rand-nth formats))))

(defn email
  ([] (str (user-name) "@" (domain-name)))
  ([name] (str (user-name name) "@" (domain-name))))

(defvar- free-domains
  ["gmail.com" "yahoo.com" "hotmail.com"])

(defn free-email
  ([] (str (user-name) "@" (rand-nth free-domains)))
  ([name] (str (user-name name) "@" (rand-nth free-domains))))

(defn emails []
  (repeatedly
    (fn []
      ((rand-nth [free-email email])))))
