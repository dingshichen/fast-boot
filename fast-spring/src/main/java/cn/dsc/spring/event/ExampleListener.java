package cn.dsc.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author dingshichen
 */
@Slf4j
@Component
public class ExampleListener implements ApplicationListener<ExampleEvent> {

    @Override
    public void onApplicationEvent(ExampleEvent event) {
        log.info("监听到了事件啦！");
        log.info("监听事件内容：{}", event.getText());
    }
}
