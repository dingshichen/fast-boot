package cn.dsc.sleuth.service.impl;

import cn.dsc.sleuth.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @NewSpan
    @Override
    public void dowork() {
        log.info("TestServiceImpl dowork");
    }

    @Override
    public void execute() {
        log.info("TestServiceImpl execute");
    }
}
