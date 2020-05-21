package cn.dsc.fasttest.conf;


import cn.dsc.fasttest.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FastConfiguration.class)
public class FastConfigurationTest {

    @Autowired
    private ConfigService configService;

    @Test
    public void configService() {
        String name = configService.configName();
        System.out.println(name);
    }
}