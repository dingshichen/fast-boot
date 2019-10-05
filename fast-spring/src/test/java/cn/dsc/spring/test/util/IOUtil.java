package cn.dsc.spring.test.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
public class IOUtil {

	public static void printInputStream(InputStream inputStream) throws IOException {
		byte[] s = new byte[1024];
		inputStream.read(s);
		log.info(new String(s));
	}
}
