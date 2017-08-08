package dbeventhandler.util;

import dbeventhandler.config.TableMappingProperties;
import dbeventhandler.domain.kafka.KafkaLog;
import dbeventhandler.domain.kafka.Table;
import dbeventhandler.config.ConfigDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class TableInfoMapper {

    @Autowired
    private TableMappingProperties tableMappingProperties;

    @Autowired
    private ConfigDynamicService configDynamicService;

    public void mapping(KafkaLog kafkalog){
        //1.check dml type
        //TODO change as constants...
        if("I".equals(kafkalog.getType())){
            System.out.println("Insert!");
        }
        else if("U".equals(kafkalog.getType())){
            System.out.println("Update!");
        }else{
            System.out.println("Delete!");
        }

        System.out.println("config server prop::  " + configDynamicService.getTableMappingRule());

        //2. check table scheme.
        //TODO Table과 DML Type으로 kafka 명세 조회
        Table table = tableMappingProperties.getTable();
        if(table.getOrgName().equals(kafkalog.getTable())) {

            System.out.println("[TableMapper.table] : " + table.toString());

            //yml에 정의 된 필드 명세
            Map columnProperties = tableMappingProperties.getColumns();
            Iterator<String> keys = columnProperties.keySet().iterator();
            while( keys.hasNext() ){
                String key = keys.next();
                System.out.println( String.format("columnProperties:: column: %s,value: %s", key, columnProperties.get(key)) );
            }

            //kafka log로 유입된 필드
            Map dmlLogColumnMap = kafkalog.getBeforeLog();
            Iterator<String> orgColumnKeys = dmlLogColumnMap.keySet().iterator();
            while( orgColumnKeys.hasNext() ){
                String orgColumn= orgColumnKeys.next();
                String targetColumnName = String.valueOf(columnProperties.get(orgColumn));
                String columnValue = String.valueOf(dmlLogColumnMap.get(orgColumn));

                System.out.println( String.format("column: %s,value: %s", orgColumn, dmlLogColumnMap.get(orgColumn)) );
                System.out.println("SQL:: " + "update "  + table.getTargetName() + " set " + targetColumnName + " = "  + columnValue );
            }



        }

    }
}
