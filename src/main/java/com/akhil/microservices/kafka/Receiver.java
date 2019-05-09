package com.akhil.microservices.kafka;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);
    
    @Autowired
    ConsumerFactory<String, String> consumerFactory;

    
    public ArrayList<String> receive(String topic) {
   	
        Consumer<String, String> consumer = consumerFactory.createConsumer();
        consumer.subscribe(Arrays.asList(topic));

        ArrayList<String> recordStrings= new ArrayList<String>();
        
        ConsumerRecords<String, String> records = consumer.poll(3000);
        
        records.forEach(record -> {
            recordStrings.add("Partition=" + record.partition() + " | Offset=" + record.offset() + " | Key=" + record.key() + " | Value=" +record.value());
        });

        consumer.commitAsync();
        consumer.close();
        
        return recordStrings;
    }


    
}