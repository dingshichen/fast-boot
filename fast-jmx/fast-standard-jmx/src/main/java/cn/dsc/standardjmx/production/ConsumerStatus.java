package cn.dsc.standardjmx.production;

import java.util.Random;

/**
 * @author dingshichen
 */
public class ConsumerStatus implements ConsumerStatusMBean {

    @Override
    public int getCurrentConsume() {
        Random random = new Random();
        return random.nextInt(20);
    }

    @Override
    public String getMessage() {
        return "consume success";
    }

}
