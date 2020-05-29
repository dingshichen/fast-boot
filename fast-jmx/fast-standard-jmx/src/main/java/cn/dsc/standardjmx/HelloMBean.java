package cn.dsc.standardjmx;

/**
 * @author dingshichen
 */
public interface HelloMBean {

    String getName();

    void setName(String name);

    String printHello();

    String printHello(String whoName);
}
