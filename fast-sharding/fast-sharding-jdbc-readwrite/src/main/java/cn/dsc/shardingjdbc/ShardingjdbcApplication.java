package cn.dsc.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingshichen
 */
@MapperScan("cn.dsc.shardingjdbc.mapper")
@SpringBootApplication
public class ShardingjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbcApplication.class, args);
    }
}
