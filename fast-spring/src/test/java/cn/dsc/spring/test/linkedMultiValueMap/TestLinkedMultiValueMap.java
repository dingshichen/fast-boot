package cn.dsc.spring.test.linkedMultiValueMap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

/**
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestLinkedMultiValueMap {

	/**
	 * LinkedMultiValueMap 是一个key可以存多个值的集合
	 */
	@Test
	public void test(){
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("key1", "value1");
		map.add("key1", "value2");
		map.add("key3", "value3");
		map.forEach((k, v) -> log.info("key : {}, value : {}", k, v));
	}
}
