package cn.dsc.ncc;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dingShiChen
 * @since 2019/8/28
 */
@SpringBootApplication
@NacosPropertySource(dataId = "ncc", autoRefreshed = true)
public class NacosConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConfigClientApplication.class, args);
	}
}
