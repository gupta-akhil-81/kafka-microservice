# kafka-microservice

This project gives following features-

1.	Gives an api to send one message to any topic in Kafka. 
		*POST http://localhost:8081/kafka/topics/topic1/messages
2.	Given an api to retrieve messages from a topic from after the last read offset position.
		*GET http://localhost:8081/kafka/topics/topic1/messages


# How to connect Google Cloud VM Kafka via External IP over public internet

Follow below steps-

1. Create a Kafka VM which is pre packaged by Google. https://console.cloud.google.com/marketplace/details/click-to-deploy-images/kafka
2. After installation is finished, edit the VM details and in the text box "Network Tags", add a tag "kafka". (You can keep any tag name)
3. Save the VM.
4. Now go to VPC Network --> Firewall Rules, and create a new Firewall rule. Keep following settings-
	*name = allow-tcp-2181-9092
	*Targets = Specified target tags
	*Target tags = kafka.   (This is the tag name that you provided in Network tags field of kafka VM)
	*Source IP Ranges = 0.0.0.0/0
	*Specified protocols and ports = tcp:9092, 2181
5. Save the Firewall Rule. Now go to your kafka VM and see the Network Details of your VM. You should be seeing this newly created Firewall rule in the Firewall Rules list of this VM.
6. Now SSH to the VM instance. And go to /opt/kafka/config
7. Edit server.properties, and do following changes-
	*Uncomment and set the property "listeners=PLAINTEXT://:9092"
	*Uncomment and set the property "advertised.listeners=PLAINTEXT://external-ip-of-kafka-vm:9092".  Put the EXTERNAL IP of your KAFKA VM in this property value.
8. Save the server.properties file.	
8. run command -systemctl restart kafka
9. run command -systemctl status kafka
10. Now try to connect to your kafka instance from outside the GCP using the external IP.
