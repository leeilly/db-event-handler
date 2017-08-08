package com.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.domain.kafka.KafkaLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogParser {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public KafkaLog parse(String contents){

        KafkaLog kafkaLog = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            kafkaLog = mapper.readValue(contents, KafkaLog.class);
            log.info("[LogParser.parse.result] " + kafkaLog.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kafkaLog;
    }

}
