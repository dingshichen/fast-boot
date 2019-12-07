package cn.dsc.test.guava.collect;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * List集合的工具类使用
 * @author dingshichen
 */
public class ListTest {

    /**
     * List的创建
     */
    @Test
    public void createCommonList(){
        ArrayList<Object> list1 = Lists.newArrayList();

        ArrayList<String> list2 = Lists.newArrayList("a", "b", "c");

        ArrayList<Object> list3 = Lists.newArrayListWithCapacity(100);

        ArrayList<String> list4 = Lists.newArrayList(list2);

        LinkedList<Object> list5 = Lists.newLinkedList();

        LinkedList<String> list6 = Lists.newLinkedList(list4);
    }




}
