package cn.dsc.spring.test.annotationUtils;

import cn.dsc.spring.FastSpringApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * 处理注解的工具类
 * @author dingShiChen
 * @since 2019/8/26
 */
@Slf4j
public class TestAnnotationUtils {

	/**
	 * 只能获取一级注释
	 * 从某个类上获取指定的注解实例，及可以获取到这个注解标注的具体属性的值
	 * 第一个参数，可以是普通类的class，也可以是注解类等特殊类的class
	 */
	@Test
	public void testGetAnnotation(){
		SpringBootApplication annotation = AnnotationUtils.getAnnotation(FastSpringApplication.class, SpringBootApplication.class);
		String[] value = annotation.scanBasePackages();
		log.info(Arrays.toString(value));
	}

	/**
	 * 只能获取一级注释
	 * 从某个Method上获取指定注解
	 */
	@Test(timeout = 10000L)
	public void testGetAnnotattionByMethod() throws ClassNotFoundException, NoSuchMethodException {
		Class cls = Class.forName("cn.dsc.spring.test.annotationUtils.TestAnnotationUtils");
		Test annotattion = AnnotationUtils.getAnnotation(cls.getMethod("testGetAnnotattionByMethod"), Test.class);
		long timeout = annotattion.timeout();
		log.info(String.valueOf(timeout));
	}

	/**
	 * 只能获取一级注释
	 * 获取某个类上所有一级注解
	 */
	@Test
	public void testGetAnnotations(){
		Annotation[] annotations = AnnotationUtils.getAnnotations(FastSpringApplication.class);
		for (Annotation annotation : annotations) {
			log.info(annotation.toString());
		}
	}

	/**
	 * 可以获取多级注解
	 * 从某个class上查找某个注解
	 */
	@Test
	public void testFindAnnotation(){
		Import annotation = AnnotationUtils.findAnnotation(FastSpringApplication.class, Import.class);
		Class[] value = annotation.value();
		log.info(Arrays.toString(value));
	}
}
