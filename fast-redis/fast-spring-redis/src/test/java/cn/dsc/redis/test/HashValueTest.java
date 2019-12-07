package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
import cn.dsc.redis.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dingshichen
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRedisApplication.class)
public class HashValueTest {

    @Resource
    private RedisTemplate<String, Person> redisTemplate;

    private HashOperations<String, String, Person> hashOperations;

    private Map<String, Person> map;

    private static final String KEY = "dkey";

    @Before
    public void before(){
        map = new HashMap<>();
        map.put("abc", new Person("dingshichen", "17705505750"));
        map.put("123", new Person("xule", "13045519090"));
        hashOperations = redisTemplate.opsForHash();
    }

    /**
     * 存入一个map
     */
    @Test
    public void putAll(){
        hashOperations.putAll(KEY, map);
    }

    /**
     * 根据hashkey，获取一个值
     */
    @Test
    public void get(){
        Person person = hashOperations.get(KEY, "123");
        log.info("get hash one value : {}", person);
    }

    /**
     * put一个值
     */
    @Test
    public void put(){
        hashOperations.put(KEY, "123", new Person());
    }

    /**
     * 删除hashkey
     */
    @Test
    public void delete(){
        hashOperations.delete(KEY, "123");
        get();
    }

    /**
     * 全删
     */
    @Test
    public void deleteAll(){
        redisTemplate.delete(KEY);
    }
}
