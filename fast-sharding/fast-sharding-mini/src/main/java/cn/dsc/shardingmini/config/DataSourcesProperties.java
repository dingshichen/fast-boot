package cn.dsc.shardingmini.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author dingshichen
 */
@Data
@ConfigurationProperties(prefix = "shardingmini.datasources")
public class DataSourcesProperties {

    private List<DataSourceProperties> datasources;
}
