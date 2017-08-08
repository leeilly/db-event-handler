package dbeventhandler.config;

import dbeventhandler.domain.kafka.Column;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RefreshScope
public class ConfigDynamicService {
    @Value("${tablemapping.table.orgName}")
    private String orgTable;
    @Value("${tablemapping.table.targetName}")
    private String targetTable;
    @NestedConfigurationProperty
    private List<Column> columns = new ArrayList<>();

    public Map<String, Object> getTableMappingRule() {
        Map<String, Object> tableMappingRule  = new HashMap<>();
        tableMappingRule.put("orgTable", orgTable);
        tableMappingRule.put("targetTable", targetTable);
        tableMappingRule.put("columnList", columns);

        return tableMappingRule;
    }

}
