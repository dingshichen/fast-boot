package cn.dsc.spring.test.resource;

import cn.dsc.spring.FastSpringApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

/**
 * {@link org.springframework.core.io.ResourceLoader} ResourceLoader接口是用来根据参数获取适合的Resource资源
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastSpringApplication.class)
public class TestResourceLoader {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void testGetResource(){
		//这里根据classpath前缀，返回的Resource是ClassPathResource
		Resource resource = applicationContext.getResource("classpath:cn.dsc.spring.FastSpringApplication");
		log.info(resource.getFilename());
	}

	@Test
	public void testGetResource2(){
		Resource resource = resourceLoader.getResource(ResourceUtils.FILE_URL_PREFIX + "/Users/dingshichen/Documents/商帆/361项目/361二期上线发版.txt");
		log.info(resource.getFilename());
	}
}
