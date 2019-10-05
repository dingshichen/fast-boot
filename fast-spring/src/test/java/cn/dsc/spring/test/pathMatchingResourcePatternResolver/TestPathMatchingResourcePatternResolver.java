package cn.dsc.spring.test.pathMatchingResourcePatternResolver;

import cn.dsc.spring.FastSpringApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * PathMatchingResourcePatternResolver
 * 用 于处理 ant 匹配风格(com/*.jsp,com/*\/.jsp),找出所有的资源, 结合上面的resource的概念一起使用,对于遍历文件很有用
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastSpringApplication.class)
public class TestPathMatchingResourcePatternResolver {

	@Autowired
	private ResourcePatternResolver resolver;

	/**
	 * 获取某个路径下的所有class，
	 * locationPattern 参数，有多种方式填写：
	 * classpath:开头，classpath*:开头，**代表多级通配符，递归查找
	 * @throws IOException
	 */
	@Test
	public void testGetResourceByPath() throws IOException {
		Resource[] resources = resolver.getResources("classpath:org/springframework/boot/admin/*.class");
		for (Resource resource : resources) {
			log.info("资源：{}", resource.getFilename());
			log.info("描述：{}", resource.getDescription());
		}
	}
}
