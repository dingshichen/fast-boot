package cn.dsc.spring.autowire;

import cn.dsc.spring.service.NullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author dingshichen
 */
@Component
public class AutowireService {


    private NullService nullService;

    /**
     * @Nullable 注解标记此注入如果对象是null，可以忽略不报错的执行下去
     * @param nullService
     */
    @Autowired
    public void setNullService(@Nullable NullService nullService) {
        if (nullService == null) {
            nullService = new NullService() {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }
        this.nullService = nullService;
    }
}
