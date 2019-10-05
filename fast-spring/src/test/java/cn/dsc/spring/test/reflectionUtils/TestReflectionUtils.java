package cn.dsc.spring.test.reflectionUtils;

import cn.dsc.spring.service.impl.PersionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的工具类
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestReflectionUtils {

	/**
	 * 获取属性
	 */
	@Test
	public void testFindField(){
		Field field = ReflectionUtils.findField(PersionServiceImpl.class, "name");
		log.info("field name : {}", field.getName());
	}

	/**
	 * 获取方法
	 */
	@Test
	public void testFindMethod(){
		Method method1 = ReflectionUtils.findMethod(PersionServiceImpl.class, "echo");
		log.info("method1 name : {}", method1.getName());
		Method method2 = ReflectionUtils.findMethod(PersionServiceImpl.class, "echo", null);
		log.info("method2 name : {}", method2.getName());
	}

	/**
	 * 反射执行
	 */
	@Test
	public void testInvoke(){
		Method method = ReflectionUtils.findMethod(PersionServiceImpl.class, "echo");
		Object result = ReflectionUtils.invokeMethod(method, new PersionServiceImpl());
		log.info("invoke success : {}", result);
	}

	/**
	 * 判断是否是 public static final
	 */
	@Test
	public void testIsPublicStaticFinal(){
		Field field = ReflectionUtils.findField(PersionServiceImpl.class, "author");
		boolean result = ReflectionUtils.isPublicStaticFinal(field);
		log.info("是否是Public static final ：{}", result);
		Field field2 = ReflectionUtils.findField(PersionServiceImpl.class, "version");
		boolean result2 = ReflectionUtils.isPublicStaticFinal(field2);
		log.info("是否是Public static final ：{}", result2);
	}

	/**
	 * 获取所有的方法
	 */
	@Test
	public void testGetAllDeclaredMethods(){
		Method[] methods = ReflectionUtils.getAllDeclaredMethods(PersionServiceImpl.class);
		for (Method method : methods) {
			log.info(method.getName());
		}
	}

	/*
		还有判断是否是一些equals、toString、Object继承的方法等

	 */
}
