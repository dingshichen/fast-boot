package cn.dsc.test.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author dingShiChen
 * @since 2019/9/19
 */
public class TestOptional {


	/**
	 * Optional.ofNullable() 接收一个对象，可以是空对象
	 * get() 返回实例，如果是null，会抛异常
	 */
	@Test
	public void testGet(){
		String text1 = null;
		String text2 = "hello";
		Optional<String> optional1 = Optional.ofNullable(text1);
		System.out.println(optional1.get());

		Optional<String> optional2 = Optional.ofNullable(text2);
		System.out.println(optional2.get());
	}

	/**
	 * ifPresent() 如果实例不为空，执行Comsumer Lambda 表达式
	 */
	@Test
	public void testIfPresent(){
		String text1 = null;
		String text2 = "hello";
		Optional<String> optional1 = Optional.ofNullable(text1);
		optional1.ifPresent(System.out::println);

		Optional<String> optional2 = Optional.ofNullable(text2);
		optional2.ifPresent(System.out::println);
	}

	/**
	 * orElse(obj) 如果实例非空，返回实例，空返回obj
	 */
	@Test
	public void testOrElse(){
		String text1 = null;
		String text2 = "hello";
		Optional<String> optional1 = Optional.ofNullable(text1);
		System.out.println(optional1.orElse("world"));

		Optional<String> optional2 = Optional.ofNullable(text2);
		System.out.println(optional2.orElse("world"));
	}

	/**
	 * orElseGet() 如果实例非空，返回实例；如果空，执行Comsumer Lambda 表达式
	 */
	@Test
	public void testOrElseGet(){
		String text1 = null;
		String text2 = "hello";
		Optional<String> optional1 = Optional.ofNullable(text1);
		System.out.println(optional1.orElseGet(() -> {
			System.out.println("optional1 is null !");
			return new String("world");
		}));

		Optional<String> optional2 = Optional.ofNullable(text2);
		System.out.println(optional2.orElseGet(() -> {
			System.out.println("optional1 is null !");
			return new String("world");
		}));
	}
}
