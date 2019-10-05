package cn.dsc.highlevel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingShiChen
 * @since 2019/9/23
 */
@MapperScan("cn.dsc.highlevel.mapper")
@SpringBootApplication
public class HighLevelClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighLevelClientApplication.class, args);
	}
}
