package cn.dsc.test.guava.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.io.Serializable;

/**
 * 创建不可变集合 先理解什么是immutable(不可变)对象
 *
 * 1.在多线程操作下，是线程安全的。
 *
 * 2.所有不可变集合会比可变集合更有效的利用资源。
 *
 * 3.中途不可改变
 *
 * 不可变容器的创建
 * @author dingshichen
 */
public class ImmtableTest {


    /**
     * 不可变容器的创建
     */
    public void createImmtable(){
        ImmutableList<String> list = ImmutableList.of("a", "b", "c");

        ImmutableSet<Integer> set = ImmutableSet.of(1, 2, 3);

        ImmutableMap<String, ? extends Serializable> map = ImmutableMap.of("f", 10, "c", "55");
    }

}
