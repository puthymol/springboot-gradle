# Kafka Guideline

https://kafka.apache.org/quickstart

### Download Kafka and extract
````
$ tar -xzf kafka_2.13-3.9.0.tgz
$ cd kafka_2.13-3.9.0
````

### Start the ZooKeeper service
````
$ bin/zookeeper-server-start.sh config/zookeeper.properties
````

### Start the Kafka Broker service
````
$ bin/kafka-server-start.sh config/server.properties
````

### Create topic quickstart-events
````
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
````

### Describe topic quickstart-events
````
$ bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
````

### List all topics
````
$ bin/kafka-topics.sh --list --bootstrap-server localhost:9092
````

### Produce messages into topic quickstart-events
````
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
````

### Consume messages from topic quickstart-events
````
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
````


# Kafka Running In Docker Container

### Create Docker container
````
$ docker run --name apache-kafka-demo -p 9092:9092 -d apache/kafka:3.7.2
````
After run this command, ZooKeeper and Kafka Broker service has been started.

### Access to Docker container
````
$ docker exec -it --workdir /opt/kafka/bin/ apache-kafka-demo sh
````
