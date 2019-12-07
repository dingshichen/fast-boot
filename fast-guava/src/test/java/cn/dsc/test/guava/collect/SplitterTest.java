package cn.dsc.test.guava.collect;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 拆装
 * @author dingshichen
 */
public class SplitterTest {

    /**
     * 字符串转成list
     */
    @Test
    public void stringSplitList(){
        String str = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").splitToList(str);
        System.out.println(list);
    }

    /**
     * 滤空字符串转List
     */
    @Test
    public void stringSplitListTrim(){
        String str = "1-2-3-4-  5-  6   ";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list);
    }

    /**
     * 字符串转Map
     */
    @Test
    public void stringSpliatMap(){
        String str = "xiaoming=11,xiaohong=23";
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);
        System.out.println(map);
    }
}
