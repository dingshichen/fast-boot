package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
import cn.dsc.redis.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author dingShiChen
 * @since 2019/11/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRedisApplication.class)
public class ObjectValueTest {

	/**
	 * 这里不能用Autowired注入，Autowired按类型注入，泛型会有问题。
	 */
	@Resource
	private RedisTemplate<String, Person> redisTemplate;

	@Test
	public void setGet(){
		redisTemplate.opsForValue().set("firstPerson", new Person("soulkey", "13045958870"), 1L, TimeUnit.MINUTES);

		Person person = redisTemplate.opsForValue().get("firstPerson");
		log.info("存入的Java对象再取出来：{}", person);
	}
}
