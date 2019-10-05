package cn.dsc.spring.test.propertiesLoaderUtils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesLoaderUtils
 * 读取资源文件的工具类
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestPropertiesLoaderUtils {

	/**
	 * 根据资源文件名称，加载并合并classpath中的所有资源文件
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		Properties properties1 = PropertiesLoaderUtils.loadAllProperties("test.properties");
		log.info(properties1.toString());
		//也可以加载xml，xml必须符合xml资源类型的DTO定义
		Properties properties2 = PropertiesLoaderUtils.loadAllProperties("test.xml");
		log.info(properties2.toString());
	}

	/**
	 * 加载读取Resource对象
	 * @throws IOException
	 */
	@Test
	public void testResource() throws IOException {
		Resource resource = new ClassPathResource("test.xml");
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);
		log.info(properties.toString());
	}

	/**
	 * 加载资源文件填充到另一个Properties中
	 * @throws IOException
	 */
	@Test
	public void testResourceProperties() throws IOException {
		Properties properties = new Properties();
		properties.put("system", "windows");
		Resource resource = new ClassPathResource("test.xml");
		PropertiesLoaderUtils.fillProperties(properties, resource);
		log.info(properties.toString());
	}
}
