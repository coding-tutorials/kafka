(ns consumer.kafka
  (:import [java.util Properties])
  (:import [org.apache.kafka.clients.consumer KafkaConsumer])
  (:require [clojure.data.json :as json]))

(def consumer
  (let [consumer (KafkaConsumer. (doto (Properties.)
                                   (.put "bootstrap.servers" "broker:29092")
                                   (.put "group.id" "consumer-group2")
                                   (.put "key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer")
                                   (.put "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer")))]
    (doto consumer
      (.subscribe ["teste1"]))))

(defn consume [count]
  (->> (.poll consumer count)
       (map #(.value %))))
   