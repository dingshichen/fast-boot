package cn.dsc.springjmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.*;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author dingshichen
 */
@RestController
@SpringBootApplication
public class FastSpringJmxClientApplication {

    @Autowired
    private MBeanServerConnectionFactoryBean bean;

    public static void main(String[] args) {
        SpringApplication.run(FastSpringJmxClientApplication.class, args);
    }

    @GetMapping("message")
    public String getMessage() throws MalformedObjectNameException, ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        MBeanServerConnection connection = bean.getObject();
        connection.invoke(new ObjectName("hello"), "setMessage", new Object[]{"bingo ~"}, null);
        Object result = connection.invoke(new ObjectName("hello"), "getMessage", null, null);
        return result.toString();
    }

    @GetMapping("print")
    public String getPrint() throws MalformedObjectNameException, ReflectionException, MBeanException, InstanceNotFoundException, IOException {
        MBeanServerConnection connection = bean.getObject();
        Object result = connection.invoke(new ObjectName("hello"), "print", null, null);
        return result.toString();
    }

    @Bean
    public MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean() throws MalformedURLException {
        MBeanServerConnectionFactoryBean bean = new MBeanServerConnectionFactoryBean();
        bean.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:10086/jmxrmi");
        return bean;
    }
}
