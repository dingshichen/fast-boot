package cn.dsc.spring.test.resource;

import cn.dsc.spring.test.util.IOUtil;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * FileSystemResource 以文件的绝对路径方式进行访问，效果类似于Java中的File
 * @author dingShiChen
 * @since 2019/8/26
 */
public class TestFileSystemResource {

	private final static String FILEURL = "C:\\Users\\dingshichen\\Desktop\\test.txt";

	@Test
	public void getInputStream() throws IOException {
		FileSystemResource resource = new FileSystemResource(FILEURL);
		InputStream inputStream = resource.getInputStream();
		IOUtil.printInputStream(inputStream);
		inputStream.close();
	}
}
