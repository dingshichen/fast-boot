package cn.dsc.sleuth.service;

import org.springframework.cloud.sleuth.SpanName;

/**
 * @author dingshichen
 */
public interface TestService {

    void dowork();

    @SpanName("haha")
    void execute();
}
