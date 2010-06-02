(ns faker.phone-number
  "Generate fake phone numbers."
  (:use (clojure.contrib [def :only (defvar-)]
                         [string :only (replace-by)])))

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
                  (fn [_] (str (rand-int 10)))
                  (rand-nth formats)))))

