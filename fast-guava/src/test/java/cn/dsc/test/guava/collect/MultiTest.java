package cn.dsc.test.guava.collect;

import com.google.common.collect.ArrayListMultimap;
import org.junit.Test;

/**
 * 多值容器
 * @author dingshichen
 */
public class MultiTest {

    /**
     * 多值map
     */
    @Test
    public void createMultiMap(){
        ArrayListMultimap<String, String> listMultimap = ArrayListMultimap.create();
        listMultimap.put("a", "ding");
        listMultimap.put("a", "shichen");
        System.out.println(listMultimap.get("a"));
    }
}
