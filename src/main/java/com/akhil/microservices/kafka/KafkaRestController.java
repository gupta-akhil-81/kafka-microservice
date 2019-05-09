package com.akhil.microservices.kafka;

import java.util.ArrayList;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaRestController {

    private final Sender sender;
    private final Receiver receiver;

    @Autowired
    KafkaRestController(Sender sender, Receiver receiver) {
        this.sender = sender;
        this.receiver=receiver;
    }

    @PostMapping(value = "/topics/{topic-name}/messages")
    public void sendToKafka(@RequestBody String message, @PathVariable("topic-name") String topicName) {
        this.sender.send(message, topicName);
    }
    
    
    @GetMapping(value = "/topics/{topic-name}/messages")
    public ArrayList<String> readFromKafka(@PathVariable("topic-name") String topicName) {
    	
    	return this.receiver.receive(topicName);
    }
    
    
}