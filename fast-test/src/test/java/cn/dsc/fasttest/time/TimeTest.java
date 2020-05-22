package cn.dsc.fasttest.time;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
public class TimeTest {

    /**
     * 测试超时，超时等待测试完成后才报错
     */
    @SneakyThrows
    @Timed(millis = 2000L)
    @Test
    public void test() {
        System.out.println("******************** test start ********************");
        Thread.sleep(3000L);
        System.out.println("******************** test end ********************");
    }
}
