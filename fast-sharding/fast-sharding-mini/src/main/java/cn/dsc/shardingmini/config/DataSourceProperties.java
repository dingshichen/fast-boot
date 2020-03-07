package cn.dsc.shardingmini.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dingshichen
 */
@Data
@ConfigurationProperties(prefix = "shardingmini.default.datasource")
public class DataSourceProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private Long connectionTimeout;

    private Integer maxPoolSize;

    private Long idleTimeout;

    private Integer minIdle;

    private String poolName;
}
