
(ns producer.kafka
  (:import [java.util Properties])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]))

(def producer
  (KafkaProducer. (doto (Properties.)
                   (.put "bootstrap.servers" "broker:29092")
                   (.put "client.id" "orders-generator")
                   (.put "key.serializer" "org.apache.kafka.common.serialization.StringSerializer")
                   (.put "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"))))

(defn produce [topic key value]
  (.get (.send producer (ProducerRecord. topic key value))))
