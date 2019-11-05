(ns producer.core
  (:require [producer.kafka :as kafka]))

(defn -main [& _]
  (loop [i 1]
    (do
      (kafka/produce "teste1" (str "message-" i) (str "message: " i))
      (println (str "message " i " produced."))
      (if (< i 10000)
        (recur (inc i)))))
  (println "acobou a paz"))

