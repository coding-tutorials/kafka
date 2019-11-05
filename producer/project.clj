(defproject producer "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.apache.kafka/kafka-clients "2.3.0"]]
  :main ^:skip-aot producer.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
