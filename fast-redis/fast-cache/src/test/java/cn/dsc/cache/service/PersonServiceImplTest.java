package cn.dsc.cache.service;

import cn.dsc.cache.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PersonServiceImplTest extends BaseTest{

    @Autowired
    private PersonService personService;

    @Test
    public void findOne() {
        personService.findOne("coco");
        personService.findOne("message");
        personService.findOne("ding");
    }

    @Test
    public void findOne2() {
        Person person = new Person("docking", "");
        Person result = personService.findOne(person);
        assertNotNull(result);
    }

    @Test
    public void findEmpty() {
        Person person = new Person("docking", "");
        Person result = personService.findEmpty(person);
        assertNotNull(result);
    }

    @Test
    public void updateOne() {
        boolean resukt = personService.updateOne("docking");
        assertTrue(resukt);
    }

    @Test
    public void deleteOne() {
        boolean result = personService.deleteOne("null");
        assertTrue(result);
    }

    @Test
    public void deleteAll() {
        personService.deleteAll("name");
    }

    @Test
    public void findOneConfig() {
        personService.findConfig("sool");
    }
}