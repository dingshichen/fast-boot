package cn.dsc.fasttest.repeat;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
public class RepeatTest {

    @SneakyThrows
    @Repeat(3)
    @Test
    public void repeat() {
        Thread.sleep(2000L);
        System.out.println("***********************  repeat");
    }
}
