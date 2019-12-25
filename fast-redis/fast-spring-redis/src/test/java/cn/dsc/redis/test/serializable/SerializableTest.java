package cn.dsc.redis.test.serializable;

import cn.dsc.redis.SpringRedisApplication;
import cn.dsc.redis.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dingshichen
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringRedisApplication.class, SerializableConfiguration.class})
public class SerializableTest {

    @Resource
    private RedisTemplate<String, Person> redisTemplate;

    @Test
    public void setGet(){
        final String key = "serializable-key";
        Person person = new Person("serialer", "13045958870");
        redisTemplate.opsForValue().set(key, person, 1L, TimeUnit.MINUTES);
        Person person1 = redisTemplate.opsForValue().get(key);
        log.info(person1.toString());
    }

    @Test
    public void hashSetGet(){
        final String key = "serializable-key";
        Person person1 = new Person("serialer", "13045958870");
        Person person2 = new Person("wooing", "17708809117");
        Map<String, Person> map = new HashMap<>();
        map.put(person1.getName(), person1);
        map.put(person2.getName(), person2);
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, 1L, TimeUnit.MINUTES);
        log.info(redisTemplate.opsForHash().entries(key).toString());
    }
}
