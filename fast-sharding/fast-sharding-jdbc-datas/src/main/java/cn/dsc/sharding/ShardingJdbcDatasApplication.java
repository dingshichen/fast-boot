package cn.dsc.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingshichen
 */
@MapperScan("cn.dsc.sharding.mapper")
@SpringBootApplication
public class ShardingJdbcDatasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDatasApplication.class, args);
    }
}
