package cn.dsc.cache.service;

import cn.dsc.cache.model.Person;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@CacheConfig(cacheNames = "super")  //定义全局的cache参数，这里定义了cacheName，如果方法上没有定义，就会自动使用此配置
@Service
public class PersonServiceImpl implements PersonService {

    private Person person = new Person("docking", "18096695050");

    /**
     * 存缓存，key的格式是PersonServiceImpl.findOne:docking
     * value可以指定多个值，这样可以从多个值中获取缓存，当获取到值后，即使方法没有执行，但是其他没有缓存值的缓存也会被更新
     * @param name
     * @return
     */
    @Cacheable(value = "PersonServiceImpl.findOne", key = "#name")
    @Override
    public Person findOne(String name) {
        if (name.equals(person.getName())) {
            return person;
        }
        return null;
    }

    /**
     * condition 此参数表示，当调条件成立时，才存入缓存，在方法执行前执行
     * unless 此参数条件表示，当此条件成立时，不存入缓存，在方法执行后执行
     * @param person
     * @return
     */
    @Cacheable(value = "person", key = "#person.name", unless = "#result == null")
    @Override
    public Person findOne(Person person) {
        if (person.getName().equals(this.person.getName())) {
            return this.person;
        }
        return null;
    }

    /**
     * 不指定key，生成的缓存key：person::Person(name=docking, phone=)
     * @param person
     * @return
     */
    @Cacheable(value = "person")
    @Override
    public Person findEmpty(Person person) {
        if (person.getName().equals(this.person.getName())) {
            return this.person;
        }
        return null;
    }

    @CachePut(value = "person", key = "#name")  //刷新缓存，方法被执行后重新刷新缓存
    @Override
    public boolean updateOne(String name) {
        if (name.equals(person.getName())) {
            person = new Person(name, "3730011");
            return true;
        }
        return false;
    }

    @CacheEvict(value = "PersonServiceImpl.findOne", key = "#name")    //清缓存
    @Override
    public boolean deleteOne(String name) {
        return true;
    }

    /**
     * allEntries = true，清除value开头的所有缓存
     * @param name
     * @return
     */
    @CacheEvict(value = "PersonServiceImpl.findOne", allEntries = true)
    @Override
    public boolean deleteAll(String name) {
        return true;
    }

    /**
     * 组合操作
     * @param name
     */
    @Caching(cacheable = {}, evict = {})
    @Override
    public void execute(String name) {

    }

    /**
     * 没有定义cacheName,就会使用全局CacheConfig配置
     * @param name
     * @return
     */
    @Cacheable(key = "#name")
    @Override
    public String findConfig(String name) {
        return "success";
    }


}
