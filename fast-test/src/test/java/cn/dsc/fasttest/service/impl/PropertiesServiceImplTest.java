package cn.dsc.fasttest.service.impl;

import cn.dsc.fasttest.service.PropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@Slf4j
@TestPropertySource(properties = {"commonName = huawei", "commonLine = 100"})
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PropertiesServiceImpl.class)
public class PropertiesServiceImplTest {

    @Autowired
    private PropertiesService propertiesService;

    @Test
    public void getCommonName() {
        log.info("看看看" + propertiesService.getCommonName());
    }

    @Test
    public void getCommonLine() {
        log.info("看看看" + propertiesService.getCommonLine());
    }
}