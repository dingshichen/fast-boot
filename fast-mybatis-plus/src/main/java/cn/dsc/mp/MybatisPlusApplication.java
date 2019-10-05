package cn.dsc.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
@SpringBootApplication
@MapperScan("cn.dsc.mp.mapper")
public class MybatisPlusApplication {

	/**
	 * 分页插件
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setLimit(50);	//你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制
		return paginationInterceptor;
	}

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusApplication.class, args);
	}
}
