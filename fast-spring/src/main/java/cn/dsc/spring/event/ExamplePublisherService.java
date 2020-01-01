package cn.dsc.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@Slf4j
@Service
public class ExamplePublisherService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void execute(String text){
        log.info("service开始干活啦");
        log.info("活干完啦");
        ExampleEvent event = new ExampleEvent(this, text);
        this.applicationEventPublisher.publishEvent(event);
        log.info("事件推送完毕");
    }
}
