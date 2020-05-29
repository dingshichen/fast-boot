package cn.dsc.standardjmx;

/**
 * @author dingshichen
 */
public class Hello implements HelloMBean {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String printHello() {
        return "hello " + name;
    }

    @Override
    public String printHello(String whoName) {
        return "hello " + whoName;
    }
}
