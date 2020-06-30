package cn.dsc.standardjmx.production;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author dingshichen
 */
public class ConsumerServer {

    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ConsumerStatusMBean consumerStatus = new ConsumerStatus();

        ObjectName helloName = new ObjectName("ConsumerStatusMBean:name=ConsumerStatus");

        server.registerMBean(consumerStatus, helloName);

        int port = 10086;

        Registry registry = LocateRegistry.createRegistry(port);

        /**
         * service:jmx:    这个是 JMX URL 的标准前缀，所有的 JMX URL 都必须以该字符串开头
         *
         * rmi:    这个是 jmx connector server 的传输协议，在这个 url 中是使用 rmi 来进行传输的
         *
         * localhost:0    这个是 jmx connector server 的 IP 和端口，也就是真正提供服务的 host 和端口，可以忽略，那么会在运行期间随意绑定一个端口提供服务
         *
         * jndi/rmi://localhost:1099/jmxrmi    这个是 jmx connector server 的路径，具体含义取决于前面的传输协议。
         * 比如该 URL 中这串字符串就代表着该 jmx connector server 的 stub 是使用 jndi api 绑定在 rmi://localhost:1099/jmxrmi 这个地址
         */
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + port + "/jmxrmi");

        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);

        cs.start();
    }
}
