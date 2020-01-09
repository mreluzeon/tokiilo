(ns tokiilo.teval
  (:gen-class))


(def state (atom {}))

(defn teval
  [l]
  (let [v (:value l)]
  (case (:type l)
    :pali (swap! state (fn [h] (assoc h :v nil)))
    :kepeken :q
    :error)))
(comment (teval {:type :pali :value 1})
  (print @state))
