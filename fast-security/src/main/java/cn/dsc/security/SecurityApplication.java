package cn.dsc.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dingShiChen
 * @since 2019/7/11
 */
@MapperScan("cn.dsc.security.mapper")
@ComponentScan(basePackages = {"cn.dsc.security.common", "cn.dsc.security.control", "cn.dsc.security.mapper",
	"cn.dsc.security.service"})
@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}
