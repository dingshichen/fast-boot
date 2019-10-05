package cn.dsc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/8/24
 */
@Configuration
@SpringBootApplication(scanBasePackages = {"cn.dsc.spring"})
public class FastSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastSpringApplication.class, args);
	}
}
