package cn.dsc.spring.test.collectionUtils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * CollectionUtils
 * 集合的工具类
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestCollectionUtils {

	static Object[] exampleArray = new Object[]{9, 7};
	static List<Object> exampleList1 = new ArrayList<>();
	static List<Object> exampleList2 = new ArrayList<>();
	static Map<String, Object> exampleMap1 = new HashMap<>();

	static {
		exampleList1.add(1);
		exampleList1.add(2);
	}

	/**
	 * 非空校验，可以校验List也可以校验Map
	 */
	@Test
	public void testNotNull(){
		boolean result1 = CollectionUtils.isEmpty(exampleList1);
		log.info("exampleList1 is null : {}", result1);
		boolean result2 = CollectionUtils.isEmpty(exampleList2);
		log.info("exampleList2 is null : {}", result2);
		boolean result3 = CollectionUtils.isEmpty(exampleMap1);
		log.info("exampleMap1 is null : {}", result3);
	}

	/**
	 * 把一个数组转换成集合
	 */
	@Test
	public void testArrayObjectToList(){
		List list = CollectionUtils.arrayToList(exampleArray);
		log.info(list.toString());
	}

	/**
	 * 把一个数组放进一个集合中
	 */
	@Test
	public void testMergeArrayIntoCollection(){
		log.info(exampleList2.toString());
		CollectionUtils.mergeArrayIntoCollection(exampleArray, exampleList2);
		log.info(exampleList2.toString());
	}

	/**
	 * 判断两个集合是否有交集
	 */
	@Test
	public void testContainsAny(){
		boolean isContainAny1 = CollectionUtils.containsAny(Arrays.asList("a", "e"), Arrays.asList("b", "c"));
		log.info("是否有交集：{}", isContainAny1);
		boolean isContainAny2 = CollectionUtils.containsAny(Arrays.asList("a", "c"), Arrays.asList("b", "c"));
		log.info("是否有交集：{}", isContainAny2);
	}


}
