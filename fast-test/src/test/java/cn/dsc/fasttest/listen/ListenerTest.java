package cn.dsc.fasttest.listen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
@TestExecutionListeners(CustomTestExecutionListener.class)
public class ListenerTest {

    @Test
    public void executeTest() {
        System.out.println("***************** executeTest success ****************");
    }
}
