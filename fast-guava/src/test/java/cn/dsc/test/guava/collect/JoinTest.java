package cn.dsc.test.guava.collect;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * 拼接操作
 * @author dingshichen
 */
public class JoinTest {

    /**
     * list拼接
     */
    @Test
    public void listJoin(){
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");

        String result = Joiner.on("-").join(list);

        System.out.println(result);
    }

    /**
     * map拼接
     */
    @Test
    public void mapJoin(){
        Map<String, Integer> map = Maps.newHashMap();
        map.put("xiaoming", 12);
        map.put("xiaohong",13);
        String result = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(result);
    }
}
