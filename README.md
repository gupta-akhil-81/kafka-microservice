# kafka-microservice

This project gives following features-

1.	Gives an api to send one message to any topic in Kafka. 
	      POST http://localhost:8081/kafka/topics/topic1/messages
2.	Given an api to retrieve messages from a topic from after the last read offset position.
        GET http://localhost:8081/kafka/topics/topic1/messages
