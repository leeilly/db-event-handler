package dbeventhandler.kafka;

import dbeventhandler.util.LogParser;
import dbeventhandler.util.TableInfoMapper;
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
    TableInfoMapper tableInfoMapper;

    @KafkaListener(topics="${kafka.topic}")
    public void processMessage(String content) {
        log.info("received content = '{}'", content);
        storage.put(content);
        tableInfoMapper.mapping(logParser.parse(content));

    }
}
