package cn.dsc.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author dingShiChen
 * @since 2019/11/1
 */
@ServletComponentScan("cn.dsc.springmvc.filter")
@SpringBootApplication
public class MVCApplication {

	public static void main(String[] args) {
		SpringApplication.run(MVCApplication.class, args);
	}
}
