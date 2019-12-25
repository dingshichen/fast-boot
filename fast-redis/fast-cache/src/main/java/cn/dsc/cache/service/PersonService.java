package cn.dsc.cache.service;

import cn.dsc.cache.model.Person;

/**
 * @author dingshichen
 */
public interface PersonService {

    Person findOne(String name);

    Person findOne(Person person);

    Person findEmpty(Person person);

    boolean updateOne(String name);

    boolean deleteOne(String name);

    boolean deleteAll(String name);

    void execute(String name);

    String findConfig(String name);
}
