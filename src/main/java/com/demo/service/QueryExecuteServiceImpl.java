package com.demo.service;

import com.demo.config.RuleConfigProperties;
import com.demo.domain.kafka.KafkaLog;
import com.demo.util.LogParser;
import com.demo.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryExecuteServiceImpl implements QueryExecuteService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RuleConfigProperties ruleConfigProperties;

    @Autowired
    LogParser logParser;

    @Autowired
    QueryBuilder queryBuilder;

    @Override
    public void updateQuery(String contents) {
        KafkaLog kafkalog = logParser.parse(contents);
        queryBuilder.query(kafkalog);
    }
}
