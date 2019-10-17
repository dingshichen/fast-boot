package cn.dsc.spring.test.beanUtil;

import lombok.Getter;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * BeanUtils 的使用
 * @author dingShiChen
 * @since 2019/9/19
 */
@Getter
public class TestBeanUtils {


	private String name;

	/**
	 * 实例化一个class对象，如果没有无参构造，会抛异常
	 */
	@Test
	public void testTnstantiateClass(){
		Person person = BeanUtils.instantiateClass(Person.class);
		System.out.println("实例化一个class : " + person);
	}

	/**
	 * 通过构造方法对象反射一个实例
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testTnstantiateConstructor() throws NoSuchMethodException {
		Person person = BeanUtils.instantiateClass(Person.class.getConstructor(String.class), "thisisonebean");
		System.out.println("实例化的Persion的name : " + person.getName());
	}

	/**
	 * 获取目标class和所有父类class的public修饰的Method
	 */
	@Test
	public void testFindMethod(){
		Method method = BeanUtils.findMethod(Person.class, "getName");
		System.out.println("获取到方法名 : " + method.getName());
	}


	/**
	 * 获取目标class自身所有的Method
	 */
	@Test
	public void testFindDeclaredMethod(){
		Method method = BeanUtils.findDeclaredMethod(Person.class, "getName");
		System.out.println("获取到方法名 : " + method.getName());
	}

	/**
	 * 获取一个class对象的所有属性描述
	 */
	@Test
	public void testGetPropertyDescriptors(){
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(Person.class);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println("--");
			System.out.println("propertyDescriptor.getName() : " + propertyDescriptor.getName());
			System.out.println("propertyDescriptor.getShortDescription() : " + propertyDescriptor.getShortDescription());
			System.out.println("propertyDescriptor.getDisplayName() : " + propertyDescriptor.getDisplayName());
			System.out.println("propertyDescriptor 获取属性类型Class名称 : " + propertyDescriptor.getPropertyType());
			System.out.println("propertyDescriptor.getPropertyEditorClass() : " + propertyDescriptor.getPropertyEditorClass());
			System.out.println("propertyDescriptor 获取读方法 : " + propertyDescriptor.getReadMethod());
			System.out.println("propertyDescriptor 获取写方法 : " + propertyDescriptor.getWriteMethod());
		}
	}

	/**
	 * 获取一个class对象的指定属性描述
	 */
	@Test
	public void testGetPropertyDescriptor(){
		PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(Person.class, "name");
		System.out.println("propertyDescriptor.getName() : " + propertyDescriptor.getName());
		System.out.println("propertyDescriptor.getShortDescription() : " + propertyDescriptor.getShortDescription());
		System.out.println("propertyDescriptor.getDisplayName() : " + propertyDescriptor.getDisplayName());
		System.out.println("propertyDescriptor 获取属性类型Class名称 : " + propertyDescriptor.getPropertyType());
		System.out.println("propertyDescriptor.getPropertyEditorClass() : " + propertyDescriptor.getPropertyEditorClass());
		System.out.println("propertyDescriptor 获取读方法 : " + propertyDescriptor.getReadMethod());
		System.out.println("propertyDescriptor 获取写方法 : " + propertyDescriptor.getWriteMethod());
	}

	/**
	 * 把相同名的属性值copy给目标对象
	 */
	@Test
	public void testCopyProperties(){
		User user = new User();
		user.setName("soulkey");
		user.setPhone("17705505750");
		user.setCreateDate(new Date());
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(user, userInfo);
		System.out.println(userInfo);
	}

	/**
	 * 测试copy具有list属性的对象，不能迭代循环copy，类型会出问题
	 */
	@Test
	public void testCopyListProperties(){
		User user = new User();
		user.setName("soulkey");
		user.setPhone("17705505750");
		user.setCreateDate(new Date());
		Relative relative = new Relative();
		relative.setName("martv");
		user.setRelatives(Collections.singletonList(relative));
		UserInfo userInfo = new UserInfo();
		BeanUtils.copyProperties(user, userInfo);
		System.out.println(userInfo);
		List<Relation> relatives = userInfo.getRelatives();
		Relation relation = relatives.get(0);
		System.out.println(relation);
	}
}
