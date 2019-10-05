package cn.dsc.spring.test.resource;

import cn.dsc.spring.test.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.FileUrlResource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileUrlResource 可以用来访问文件对象
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
public class TestFileUrlResource {

	private final static String FILEURL = "C:\\Users\\dingshichen\\Desktop\\test.txt";

	/**
	 * 从 FileUrlResource 中获取输入流
	 * @throws IOException
	 */
	@Test
	public void testGetInputStream() throws IOException {
		FileUrlResource resource = new FileUrlResource(FILEURL);
		InputStream inputStream = resource.getInputStream();
		IOUtil.printInputStream(inputStream);
		inputStream.close();
	}

	/**
	 * 从 FileUrlResource 中获取 File
	 * @throws IOException
	 */
	@Test
	public void testGetFile() throws IOException {
		FileUrlResource resource = new FileUrlResource(FILEURL);
		File file = resource.getFile();
		FileReader reader = new FileReader(file);
		char[] s = new char[1024];
		reader.read(s);
		log.info(new String(s));
		reader.close();
	}
}
