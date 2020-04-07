package cn.dsc.p6spy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingshichen
 */
@MapperScan("cn.dsc.p6spy.mapper")
@SpringBootApplication
public class P6spyApplication {

    public static void main(String[] args) {
        SpringApplication.run(P6spyApplication.class, args);
    }
}
