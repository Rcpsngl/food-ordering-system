package com.food.ordering.system.kafka.producer.service;

import org.apache.avro.specific.SpecificRecordBase;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    void send(String topicName, K key, V message, java.util.function.Consumer<org.springframework.kafka.support.SendResult<K, V>> onSuccess, java.util.function.Consumer<Throwable> onFailure);
}