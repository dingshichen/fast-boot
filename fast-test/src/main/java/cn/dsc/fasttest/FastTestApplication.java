package cn.dsc.fasttest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingshichen
 */
@MapperScan("cn.dsc.fasttest.mapper")
@SpringBootApplication
public class FastTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastTestApplication.class, args);
    }
}
