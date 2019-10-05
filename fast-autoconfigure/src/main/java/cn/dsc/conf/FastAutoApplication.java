package cn.dsc.conf;

import cn.dsc.conf.service.UseStarterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author dingShiChen
 * @since 2019/8/29
 */
@SpringBootApplication
public class FastAutoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(FastAutoApplication.class, args);
		UseStarterService starterService = run.getBean(UseStarterService.class);
		starterService.echo();
	}
}
