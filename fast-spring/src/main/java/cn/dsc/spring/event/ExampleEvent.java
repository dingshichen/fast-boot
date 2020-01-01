package cn.dsc.spring.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author dingshichen
 */
@Getter
public class ExampleEvent extends ApplicationEvent {

    private String text;

    /**
     *
     * @param source 事件发布的类实例自身
     * @param text
     */
    public ExampleEvent(Object source, String text) {
        super(source);
        this.text = text;
    }
}
