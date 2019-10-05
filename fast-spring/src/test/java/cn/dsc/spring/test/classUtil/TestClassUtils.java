package cn.dsc.spring.test.classUtil;

import cn.dsc.spring.FastSpringApplication;
import cn.dsc.spring.service.impl.PersionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ClassUtils;

/**
 * Class 处理的工具类
 * @author dingShiChen
 * @since 2019/8/27
 */
@Slf4j
public class TestClassUtils {

	/**
	 * 获取类加载器
	 */
	@Test
	public void testGetClassLoader(){
		ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
	}

	/**
	 * 更强大的 Class.forName
	 * 内置有缓存的java基本类型，以及格式的校验
	 */
	@Test
	public void testForName() throws ClassNotFoundException {
		Class<?> cls = ClassUtils.forName("cn.dsc.spring.FastSpringApplication", null);
		log.info(cls.getSimpleName());
	}

	/**
	 * 把资源路径转换成类路径
	 * 如：cn/dsc/spring/FastSpringApplication 转换成 cn.dsc.spring.FastSpringApplication
	 */
	@Test
	public void testConvertResourcePathToClassName(){
		String className = ClassUtils.convertResourcePathToClassName("cn/dsc/spring/FastSpringApplication");
		log.info("convert result : {}", className);
	}

	/**
	 * 把类路径转换成资源路径
	 * 如：cn.dsc.spring.FastSpringApplication 转换成 cn/dsc/spring/FastSpringApplication
	 */
	@Test
	public void testConvertClassNameToResourcePath(){
		String resourcePath = ClassUtils.convertClassNameToResourcePath("cn.dsc.spring.FastSpringApplication");
		log.info("convert result : {}", resourcePath);
	}

	/**
	 * 从class对象获取到包路径 example:cn/dsc/spring
	 */
	@Test
	public void testGetclassPackageAsResourcePath(){
		String packageName = ClassUtils.classPackageAsResourcePath(FastSpringApplication.class);
		log.info("获取到包路径：{}", packageName);
	}

	/**
	 * 获取packageName
	 */
	@Test
	public void testGetPackageName(){
		String packageName1 = ClassUtils.getPackageName(FastSpringApplication.class);
		log.info("获取到包路径：{}", packageName1);
	}


	/**
	 * 从object上获取他实现的接口
	 */
	@Test
	public void testGetInterfaces(){
		Class<?>[] interfaces = ClassUtils.getAllInterfaces(new PersionServiceImpl());
		//转换成集合显示的输出
		String names = ClassUtils.classNamesToString(interfaces);
		log.info("从object获取到他的接口：{}", names);
	}

	/**
	 * 从class上获取他的接口
	 */
	@Test
	public void testGetInterfacesByClass(){
		Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(PersionServiceImpl.class);
		String names = ClassUtils.classNamesToString(interfaces);
		log.info("从class获取到他的接口：{}", names);
	}

	/**
	 * 获取class 的 singlename
	 */
	@Test
	public void testGetShortName(){
		String shortName1 = ClassUtils.getShortName(FastSpringApplication.class);
		log.info("singlename : {}", shortName1);
		String shortName2 = ClassUtils.getShortName("cn.dsc.spring.FastSpringApplication");
		log.info("singlename : {}", shortName2);
	}

	/*
		还有很多关于 Method、construct 的方法

	 */
}
