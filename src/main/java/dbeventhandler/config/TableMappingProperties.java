package dbeventhandler.config;

import dbeventhandler.domain.kafka.Table;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tablemapping")
public class TableMappingProperties {

    private Table table = new Table();
    public Table getTable(){
        return table;
    }

    @NestedConfigurationProperty
    private Map columns;

    public Map getColumns() {
        return columns;
    }

    public void setColumns(Map columns) {
        this.columns = columns;
    }

}