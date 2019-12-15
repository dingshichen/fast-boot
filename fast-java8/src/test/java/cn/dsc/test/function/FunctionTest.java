package cn.dsc.test.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * Function 接口定义了两个泛型R、T，R代表返回值，T代表参数，它没有具体的逻辑，具体的操作来自我们传入的lambda表达式
 * 在函数式编程之前我们定义一组操作首先想到的是定义一个方法，然后指定传入参数，返回我们需要的结果。
 * 函数式编程的思想是先不去考虑具体的行为，而是先去考虑参数，具体的方法我们可以后续再设置。
 * @author dingshichen
 */
public class FunctionTest {

    @Test
    public void apply(){
        Function<String, Integer> getLength = String::length;
        String name = "dingshichen";
        Integer count = getLength.apply(name);
        System.out.println(count);
    }

    @Test
    public void apply2(){
        Function<Integer, String> getValue = i -> {
            i++;
            return String.valueOf(i);
        };
        String value = getValue.apply(10);
        System.out.println(value);
    }

    /**
     * 获取一个字符串
     * @param function
     * @param number
     * @return
     */
    private String getStr(Function<Integer, String> function, Integer number){
        return function.apply(number);
    }

    /**
     * 我们通过传入不同的Function，实现了在同一个方法中实现不同的操作。
     * 在实际开发中这样可以大大减少很多重复的代码，比如我在实际项目中有个新增用户的功能，但是用户分为VIP和普通用户，且有两种不同的新增逻辑。
     * 那么此时我们就可以先写两种不同的逻辑。除此之外，这样还让逻辑与数据分离开来，我们可以实现逻辑的复用。
     */
    @Test
    public void apply3(){
        String str1 = this.getStr(i -> {
            i --;
            return String.valueOf(i);
        }, 10);

        String str2 = this.getStr(i -> {
            i ++;
            return String.valueOf(i);
        }, 10);

        System.out.println("同一个方法执行两种策略，" + str1 + "，" + str2);
    }

    /**
     * compose是先执行传入的函数，再执行当前function的函数
     * andThen是反过来
     */
    @Test
    public void compose1(){
        Function<String, String> fun = s -> {
            System.out.println("fun.apply : " + s);
            return s + "-apply";
        };

        Function<Object, String> compose = fun.compose(s -> {
            System.out.println("fun.compose : " + s);
            return s + "+compose";
        });

        String result = compose.apply(5);
        System.out.println(result);
    }
}
