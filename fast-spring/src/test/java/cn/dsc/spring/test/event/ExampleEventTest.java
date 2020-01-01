package cn.dsc.spring.test.event;

import cn.dsc.spring.FastSpringApplication;
import cn.dsc.spring.event.ExamplePublisherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastSpringApplication.class)
public class ExampleEventTest {

    @Autowired
    private ExamplePublisherService service;

    @Test
    public void event(){
        service.execute("啦啦啦啦啦");
    }
}
