package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * redis的list结构是双向链表，所以数据读取写入的顺序需要注意。
 * 由于redis是单线程的缓存数据库，对链表结构的数据进行遍历操作时，可以考虑直接序列化写入一个list集合
 * @author dingShiChen
 * @since 2019/11/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRedisApplication.class)
public class ListValueTest {

	/**
	 * 这里不能用Autowired注入，Autowired按类型注入，泛型会有问题。
	 * 第一个泛型是 k 的类型，第二个泛型是 list 中 v 的类型。
	 */
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	private static final String KEY = "rnamelist";

	private List<String> list = Arrays.asList("1", "2", "3");

	/**
	 * 存入list
	 * 由于redis的list是双向链表结构，所以可以从左或者从右push，遍历也可以从两边遍历，根据情况优选
	 */
	@Test
	public void set(){
		redisTemplate.opsForList().rightPushAll(KEY, list);
	}

	/**
	 * 查出整个List集合
	 */
	@Test
	public void getAll(){
		List<String> lists = redisTemplate.opsForList().range(KEY, 0, -1);
		log.info("取出的所有集合缓存：{}", lists);
	}

	/**
	 * 查出某个索引的值
	 * 返回的List不会为null，没有值就是空集合
	 */
	@Test
	public void getIndex(){
		List<String> lists = redisTemplate.opsForList().range(KEY, 1, 1);
		log.info("取出的所有集合缓存：{}", lists);
	}

	/**
	 * 继续添加元素
	 */
	@Test
	public void set2(){
		redisTemplate.opsForList().rightPushAll(KEY, list);
		redisTemplate.opsForList().rightPush(KEY, "4");
		redisTemplate.opsForList().rightPush(KEY, "5");
		List<String> lists = redisTemplate.opsForList().range(KEY, 0, -1);
		log.info("添加完成之后的集合：{}", lists);
	}

	/**
	 * 清除整个list
	 */
	@Test
	public void clear(){
		while (redisTemplate.opsForList().size(KEY) > 0){
			log.info("rnamelist 在缓存中有值");
			//从左移除
			redisTemplate.opsForList().leftPop(KEY);
		}
	}
}
