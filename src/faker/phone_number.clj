(ns faker.phone-number
  "Generate fake phone numbers."
  (:use (clojure.contrib [def :only (defvar-)]
                         [string :only (replace-by)])
	faker.repeatable))

(defvar- formats
  ["###-###-####",
   "(###)###-####",
   "1-###-###-####",
   "###.###.####",
   "###-###-####",
   "(###)###-####",
   "1-###-###-####",
   "###.###.####",
   "###-###-#### x###",
   "(###)###-#### x###",
   "1-###-###-#### x###",
   "###.###.#### x###",
   "###-###-#### x####",
   "(###)###-#### x####",
   "1-###-###-#### x####",
   "###.###.#### x####",
   "###-###-#### x#####",
   "(###)###-#### x#####",
   "1-###-###-#### x#####",
   "###.###.#### x#####"])

(defn phone-numbers []
  "Lazy sequence of random phone numbers."
  (repeatedly
    (fn []
      (replace-by #"#"
                  (fn [_] (str (repeatable-rand-int 10)))
                  (repeatable-rand-nth formats)))))

