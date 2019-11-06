(ns producer.core
  (:require [producer.kafka :as kafka]))

(defn create-key-value [number]
  (vector (str "key-" number) (str "value-" number)))

(defn create-values [count from]
  (let [starting-number (* count from)]
    (take count
      (iterate
        #(conj %1)
        starting-number))))


(defn -main [& _]
  (loop [i 0]
    (let [values (create-values 50 i)
          key-values (map #(create-key-value %1) values)]
      (-> (kafka/produce "test-topic" key-values)
        (println)))
    (when (< i 19)
      (recur (inc i))))
  (println "acobou a paz"))

