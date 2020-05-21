package cn.dsc.fasttest.conf;

import cn.dsc.fasttest.service.AppleService;
import cn.dsc.fasttest.service.BalanaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
    @ContextConfiguration(classes = AppleConfiguration.class),
    @ContextConfiguration(classes = BalanaConfiguration.class)
})
public class AppleAndBalanaTest {

    @Autowired
    private AppleService appleService;

    @Autowired
    private BalanaService balanaService;

    @Test
    public void ab() {
        System.out.println(appleService.getApple());
        System.out.println(balanaService.getBalana());
    }
}

