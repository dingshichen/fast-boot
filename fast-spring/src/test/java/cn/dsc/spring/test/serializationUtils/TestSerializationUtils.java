package cn.dsc.spring.test.serializationUtils;

import cn.dsc.spring.service.impl.PersionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.SerializationUtils;

/**
 * SerializationUtils 序列化和返序列化的工具类
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestSerializationUtils {

	/**
	 * 这个类提供序列化和反序列化两个方法
	 */
	@Test
	public void testSerialize(){
		PersionServiceImpl persionService = new PersionServiceImpl();
		persionService.setName("serialization-per");
		byte[] bytes = SerializationUtils.serialize(persionService);
		testDeserialize(bytes);
	}



	public void testDeserialize(byte[] bytes){
		Object object = SerializationUtils.deserialize(bytes);
		PersionServiceImpl persionService = (PersionServiceImpl) object;
		log.info(persionService.getName());
	}
}
