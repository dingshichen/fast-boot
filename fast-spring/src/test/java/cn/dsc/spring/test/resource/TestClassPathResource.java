package cn.dsc.spring.test.resource;

import cn.dsc.spring.test.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPathResource 以类路径的方式访问
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
public class TestClassPathResource {

	@Test
	public void getInputStream() throws IOException {
		//对应的文件路径：D：//adasdadsadadad
		ClassPathResource resource = new ClassPathResource("test.txt");
		InputStream inputStream = resource.getInputStream();
		IOUtil.printInputStream(inputStream);
		inputStream.close();
	}


}
