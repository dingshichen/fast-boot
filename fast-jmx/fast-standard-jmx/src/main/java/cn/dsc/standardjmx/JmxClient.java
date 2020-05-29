package cn.dsc.standardjmx;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Set;

/**
 * @author dingshichen
 */
public class JmxClient {

    public static void main(String[] args) throws IOException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException, IntrospectionException, MalformedObjectNameException {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:10086/jmxrmi");

        JMXConnector jmxc = JMXConnectorFactory.connect(url,null);

        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("MyMBean:name=HelloWorld");

        print("Domains:---------------");
        String[] domains = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            print("Domain[" + i +"] = " + domains[i]);
        }
        print();

        //MBean count
        print("MBean count:---------------");
        print("MBean count = " + mbsc.getMBeanCount());
        print();

        //process attribute
        print("process attribute:---------------");
        mbsc.setAttribute(mbeanName, new Attribute("Name", "newName")); //set value
        print("Name = " + mbsc.getAttribute(mbeanName, "Name")); //get value
        print();

        //invoke via proxy
        print("invoke via proxy:---------------");
        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, HelloMBean.class, false);
        print(proxy.printHello());
        print(proxy.printHello("zhangsan"));
        print();

        //invoke via rmi
        print("invoke via rmi:---------------");
        print(mbsc.invoke(mbeanName, "printHello", null, null));
        print(mbsc.invoke(mbeanName, "printHello", new Object[] { "lisi" }, new String[] { String.class.getName() }));
        print();

        //get mbean information
        print("get mbean information:---------------");
        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);
        print("Hello Class:" + info.getClassName());
        print("Hello Attribute:" + info.getAttributes()[0].getName());
        print("Hello Operation:" + info.getOperations()[0].getName());
        print();

        //ObjectName of MBean
        print("ObjectName of MBean:---------------");
        Set<ObjectInstance> set = mbsc.queryMBeans(null, null);
        set.forEach(e -> print(e.getObjectName()));

        jmxc.close();
    }
    
    
    private static void print() {
        System.out.println();
    }
    
    private static void print(Object message) {
        System.out.println(message);
    }
}
