package cn.dsc.spring.test.resource;

import cn.dsc.spring.test.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * UrlResource 可以用来访问网络资源
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
public class TestUrlResource {

	private static final String URL = "https://www.baidu.com/";

	@Test
	public void testGetInputStream() throws IOException {
		UrlResource resource = new UrlResource(URL);
		InputStream inputStream = resource.getInputStream();
		IOUtil.printInputStream(inputStream);
		inputStream.close();
	}
}
