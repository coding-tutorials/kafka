(ns consumer.core
  (:require [consumer.kafka :as kafka]))

(defn -main [& _]
  (while true
    (do
      (println "getting more records...")
      (let [records (kafka/consume 5)]
        (doseq [record records]
          (println (str "record: " record))))
      (Thread/sleep 3000))))

