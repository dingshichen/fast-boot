package cn.dsc.springjmx.mbean;

/**
 * @author dingshichen
 */
public interface HelloMBean {

    String getMessage();

    void setMessage(String message);

    String print();

    String print(String message);
}
