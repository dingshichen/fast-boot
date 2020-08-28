package cn.dsc.spring.test.resolvableType;

import org.junit.Test;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Type;

/**
 * 泛型工具类
 * @author dingshichen
 */
public class TestResolvableType {

    /**
     * 获取子类或者实现类上定义的范型
     */
    @Test
    public void getType() {
        ResultExtend extend = new ResultExtend();
        Type type = GenericTypeResolver.resolveTypeArgument(extend.getClass(), Result.class);
        System.out.println(type.getTypeName());
    }
}
