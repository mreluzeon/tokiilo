(ns tokiilo.tparser
  (:use [blancas.kern.core])
  (:gen-class))

(def spaces1
  (many1 (one-of* " \t\n")))

(def spaces
  (many (one-of* " \t\n")))

(def parse-consonant
  (one-of* "jklmnpstw"))

(def parse-vowel
  (one-of* "aouie"))

(def parse-syllable
  (<|>
    (<:> (<*> parse-consonant parse-vowel (sym* \n)))
    (<:> (<*> parse-consonant parse-vowel))
    (<:> (<*> parse-vowel (sym* \n)))
    (<*> parse-vowel)))

(def parse-name
  (bind [b (one-of* "AOIUEJKLMNPSTW")
         l (many1 parse-syllable)]
        (return (str b (apply str (apply concat l))))))

(def parse-object-type
  (bind [action (<|>
                  (token* "nanpa")
                  (token* "ma")
                  (token* "ijo")
                  (token* "nimi"))]
        (return (keyword action))))

(def parse-pair
  (bind [t parse-object-type _ spaces1
         v parse-name]
        (return {:type t
                 :name v})))

(def parse-action
  (bind [action (<|>
                  (token* "pali")
                  (token* "kepeken"))]
        (return (keyword action))))

(def args-separator
  (<|> (>> (token* "tawa") spaces1) (>> (token* "e") spaces1)))

(def parse-args
  (>> args-separator
      (sep-by args-separator parse-pair)))

(def parse-mi
  (bind [_ (token* "mi wile") _ spaces1
         action parse-action _ spaces1
         args   parse-args _ (sym* \.) _ spaces]
        (return {:type :pali :value args})))

(def parse-sentence
  )

(comment
  (run parse-mi "mi wile pali e nanpa Nulo.")
  (run parse-args "e nanpa Nule e ma Pona")
  )

(comment
(def parse-object
  (<|> (token* "ijo")
       (token* "mi")
       (token* "ma")))


(def parse-wile
  (bind [object parse-object _ spaces1
         object-name (optional parse-name) _ spaces
         _ (optional (token* "li")) _ spaces
         _ (token* "wile") _ spaces
         action parse-action _ spaces
         action-name parse-name _ spaces]
        (return {:type object :name object-name :value action :value-name action-name})))
)
(comment
  (run parse-wile "mi wile kepeken Juli")
  (run parse-action "pali")
  (use 'blancas.kern.core))
