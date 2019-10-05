package cn.dsc.test.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream
 * @author dingShiChen
 * @since 2019/8/28
 */
public class TestStream {

	private List<String> list = Arrays.asList("a", "b", "c", "d");

	private List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8, 8);

	/**
	 * 获取流的第一种方式：
	 * 顺序流
	 */
	@Test
	public void test1(){
		Stream<String> stream = list.stream();		//顺序流
		stream.forEach(System.out::println);
	}

	/**
	 * 并行流
	 */
	@Test
	public void test2(){
		Stream<String> parallelStream = list.parallelStream();		//并行流
		parallelStream.forEach(System.out::println);
	}

	/**
	 * 通过Arrays工具类获取流
	 */
	@Test
	public void test3(){
		String[] strs = new String[3];
		strs[0] = "1";
		strs[1] = "2";
		strs[2] = "3";
		Stream<String> stream = Arrays.stream(strs);
		stream.forEach(System.out::println);
	}

	/**
	 * 通过Stream类获取流
	 */
	@Test
	public void test04(){
		Stream<String> stringStream = Stream.of("1", "2", "3");
		stringStream.forEach(System.out::println);
	}

	/**
	 * iterate 无限流
	 * limit 截断流
	 */
	@Test
	public void test05(){
		Stream<Integer> stream = Stream.iterate(0, x -> x + 2).limit(10);
		stream.forEach(System.out::println);
	}

	/**
	 * 流筛选，接受Lambda函数参数，从流中排除某些元素
	 */
	@Test
	public void test06(){
		Stream<Integer> istream = ints.stream().filter(e -> e < 5);
		istream.forEach(System.out::println);
	}

	/**
	 * limit 截断流
	 */
	@Test
	public void test07(){
		Stream<Integer> istream = ints.stream().filter(e -> e < 5).limit(2);
		istream.forEach(System.out::println);
	}

	/**
	 * skip(n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。
	 */
	@Test
	public void test08(){
		Stream<Integer> istream = ints.stream().filter(e -> e < 5).skip(2);
		istream.forEach(System.out::println);
	}

	/**
	 * distinct 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
	@Test
	public void test09(){
		Stream<Integer> istream = ints.stream().distinct();
		istream.forEach(System.out::println);
	}

	/**
	 * 映射①map 接收 Lambda ， 将元素转换成其他形式或提取元素里的属性。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
	 */
	@Test
	public void test10(){
		Stream<String> stream = list.stream().map(String::toUpperCase);
		stream.forEach(System.out::println);

		Stream<Integer> intStream = ints.stream().map(e -> e + 5);
		intStream.forEach(System.out::println);
	}

	/**
	 * sorted 定制排序
	 */
	@Test
	public void test11(){
		Stream<Integer> intStream = ints.stream().sorted((x, y) -> y - x);
		intStream.forEach(e -> System.out.println("排序后：" + e));
	}

	/**
	 * allMatch 检查是否匹配全部元素
	 */
	@Test
	public void test12(){
		boolean b = ints.stream().allMatch(e -> e > 0);
		System.out.println("b:" + b);
	}

	/**
	 * anyMatch 检查是否匹配全部元素
	 */
	@Test
	public void test13(){
		boolean b = ints.stream().anyMatch(e -> e > 9);
		System.out.println("b:" + b);
	}

	/**
	 * noneMatch 检查是否没有匹配的元素
	 */
	@Test
	public void test14(){
		boolean b = ints.stream().noneMatch(e -> e > 10);
		System.out.println("b:" + b);
	}

	/**
	 * findFirst——返回第一个元素
	 * findAny——返回当前流中的任意元素(在串行流中返回的和findFirst一样，在并行流中返回的是处理最快的那个线程)
	 * count——返回流中元素的总个数
	 * max——返回流中最大值
	 * min——返回流中最小值
	 */
	@Test
	public void test15(){

	}

	/**
	 * 归约，reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
	@Test
	public void test16(){
		Integer sum = ints.stream().reduce(0, (x, y) -> x + y);
		System.out.println("sum:" + sum);
		Optional<Integer> sum2 = ints.stream().reduce((x, y) -> x + y);
		System.out.println("sum2:" + sum2.get());
		Optional<Integer> sum3 = ints.stream().reduce(Integer::sum);
		System.out.println("sum3:" + sum3.get());
	}

	/**
	 * collect 收集
	 */
	@Test
	public void test17(){
		List<String> collect1 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("新集合：" + collect1);

		Set<Integer> collect2 = ints.stream().map(e -> e + 1).collect(Collectors.toSet());
		System.out.println("新集合：" + collect2);

		Map<String, String> collect3 = list.stream().map(String::toUpperCase).collect(Collectors.toMap(e -> e, String::toLowerCase));		//获取到stream流再用map方法转换，再收集成新的map集合，key是元素原值，value是转小写后的值
		collect3.forEach((k, v) -> System.out.println("key:" + k + ", value:" + v));
	}
}
