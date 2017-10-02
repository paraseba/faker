(ns faker.internet
  "Generate fake domains and emails."
  (:require
     [clojure.string :as string]
     [faker.company :as co]
     [faker.name :as na]))

(def ^{:private true} suffixes ["co.uk" "com" "us" "uk" "ca" "biz" "info" "name"])

(defn domain-suffix
  "Return a random domain suffix, like com or ca"
  []
  (rand-nth suffixes))

(defn domain-word
  "Return a random domain name, like a main company name."
  []
  (-> (first (co/names))
    (string/split #" ")
    first
    (string/replace #"\W" "")
    string/lower-case))

(defn domain-name
  "Return a random domain name."
  []
  (str (domain-word) "." (domain-suffix)))

(defn- clean [s]
  (string/replace s #"\W" ""))

(def ^{:private true} formats
  [#(string/lower-case (clean (na/first-name)))
   #(string/lower-case (string/join (rand-nth ["." "-"])
                                    [(clean (na/first-name))
                                     (clean (na/last-name))]))])

(defn user-name
  "Return a random user name for email address."
  ([name]
   (-> name
     (string/split #"\W+")
     shuffle
     (->> (string/join (rand-nth ["." "-"])))
     string/lower-case))

  ([] ((rand-nth formats))))

(defn email
  "Return a random email address."
  ([] (str (user-name) "@" (domain-name)))
  ([name] (str (user-name name) "@" (domain-name))))

(def ^{:private true} free-domains
  ["gmail.com" "yahoo.com" "hotmail.com"])

(defn free-email
  "Return a random free email address."
  ([] (str (user-name) "@" (rand-nth free-domains)))
  ([name] (str (user-name name) "@" (rand-nth free-domains))))

(defn emails
  "Lazy sequence of random emails."
  []
  (repeatedly
    (fn []
      ((rand-nth [free-email email])))))
