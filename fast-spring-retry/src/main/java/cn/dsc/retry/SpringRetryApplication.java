package cn.dsc.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author dingShiChen
 * @since 2019/9/20
 */
@EnableRetry
@SpringBootApplication
public class SpringRetryApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRetryApplication.class, args);
	}
}
