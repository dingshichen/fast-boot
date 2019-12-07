package cn.dsc.redis.test;

import cn.dsc.redis.SpringRedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author dingshichen
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRedisApplication.class)
public class SetValueTest {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private SetOperations<String, String> setOperations;

    private static final String KEY = "setkey";

    @Before
    public void before(){
        setOperations = redisTemplate.opsForSet();
    }

    /**
     * 添加
     */
    @Test
    public void put(){
        setOperations.add(KEY, "dingshichen", "lili", "wallo", "lili");
    }

    /**
     * 取出
     */
    @Test
    public void getAll(){
        Set<String> members = setOperations.members(KEY);
        log.info("取出所有：{}", members);
    }

    /**
     * 删除指定元素
     */
    @Test
    public void deleteOne(){
        setOperations.remove(KEY, "lili");
        getAll();
    }

    /**
     * 删除所有
     */
    @Test
    public void deleteAll(){
        redisTemplate.delete(KEY);
        getAll();
    }
}
