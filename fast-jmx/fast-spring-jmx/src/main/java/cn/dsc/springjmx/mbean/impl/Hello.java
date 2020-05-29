package cn.dsc.springjmx.mbean.impl;

import cn.dsc.springjmx.mbean.HelloMBean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author dingshichen
 */
@Component
@ManagedResource(
        objectName = "hello:name=HelloMBean",
        description = "你想看的 Bean"
)
public class Hello implements HelloMBean {

    private String message;

    /**
     * 暴露属性
     * @return
     */
    @ManagedAttribute(description = "消息内容")
    @Override
    public String getMessage() {
        return this.message;
    }

    @ManagedAttribute(description = "消息内容")
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 暴露操作
     * @return
     */
    @ManagedOperation(description = "打印它")
    @Override
    public String print() {
        return "hello " + this.message;
    }

    @ManagedOperation(description = "打印它")
    @Override
    public String print(String message) {
        return "hello " + message;
    }
}
