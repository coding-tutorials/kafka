
(ns producer.kafka
  (:import [java.util Properties])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord Callback]))

(def producer
  (KafkaProducer. (doto (Properties.)
                   (.put "bootstrap.servers" "broker:29092")
                   (.put "group.id" "kafka-test")
                   (.put "acks" "all")
                   (.put "transactional.id" "my-transactional-id")
                   (.put "enable.idempotence" "true")
                   (.put "key.serializer" "org.apache.kafka.common.serialization.StringSerializer")
                   (.put "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"))))

(.initTransactions producer)

(def callback
  (reify Callback
    (onCompletion [_  metadata e]
      (if e
        (println "error while saving record")
        (println (str "record saved " (.offset metadata)))))))

(defn send-key-values [topic key-values]
  (doall
    (map
      (fn [[key value]]
        (.send producer (ProducerRecord. topic key value) callback))
      key-values)))


(defn produce [topic key-values]
  (do (.beginTransaction producer)
    (let [result (send-key-values topic key-values)]
      (.commitTransaction producer)
      result)))
    


(defn close []
  (.close producer))
