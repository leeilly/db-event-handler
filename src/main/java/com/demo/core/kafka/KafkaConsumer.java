package com.demo.core.kafka;

import com.demo.service.QueryExecuteService;
import com.demo.util.LogParser;
import com.demo.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    MessageStorage storage;

    @Autowired
    LogParser logParser;

    @Autowired
    QueryBuilder tableInfoMapper;

    @Autowired
    QueryExecuteService queryExecuteService;

    @KafkaListener(topics="${kafka.topic}")
    public void processMessage(String content) {
        log.info("received content = '{}'", content);
        storage.put(content);
        queryExecuteService.updateQuery(content);
        //tableInfoMapper.mapping(logParser.parse(content));

    }
}
