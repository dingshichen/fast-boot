package cn.dsc.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DingShiChen
 * @date 2020/3/1
 */
public class HeapOOM {

    static class OOMObject {

    }

    /**
     * VM 参数
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/dingshichen/Documents/heapdump.hprof
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        for(;;) {
            list.add(new OOMObject());
        }
    }
}
