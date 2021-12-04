package com.marcohnp.votingsession.kafka.producer;

import com.marcohnp.votingsession.kafka.model.SessaoKafkaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessaoKafkaProducer {

    private final String topic;
    private final KafkaTemplate<String, SessaoKafkaModel> kafkaTemplate;

    public SessaoKafkaProducer(@Value("${kafka.topic}") String topic,
                                KafkaTemplate<String, SessaoKafkaModel> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(SessaoKafkaModel sessaoKafkaModel) {
        kafkaTemplate.send(topic, sessaoKafkaModel).addCallback(
                success -> log.info("Mensagem enviada: {}", success.getProducerRecord().value()),
                failure -> log.info("Erro ao envia mensagem: {}", sessaoKafkaModel)
        );
    }
}
