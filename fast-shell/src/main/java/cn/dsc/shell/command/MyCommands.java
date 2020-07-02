package cn.dsc.shell.command;

import cn.dsc.shell.model.User;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.List;

/**
 * 默认按类分组，会将类名按驼峰拆分成字符
 *
 * @author dingshichen
 */
@ShellComponent
public class MyCommands {

    /**
     * key 是指定命令名，默认是方法名，如果指定了 key，方法名就失效了
     *
     * @param a
     * @param b
     * @return
     */
    @ShellMethod(value = "Add two integers together.", key = {"sum", "plus"})
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 驼峰方法名会被转化成中划线
     *
     * @param text
     * @return
     */
    @ShellMethod("打印.")
    public String printString(String text) {
        return "打印结果：" + text;
    }

    /**
     * 参数默认值
     *
     * @param who
     * @return
     */
    @ShellMethod("Say hello.")
    public String greet(@ShellOption(defaultValue = "dingshichen") String who) {
        return "hello " + who;
    }

    /**
     * 打印 String 集合，打印出来的是自动换行的字符
     *
     * @param a
     * @param b
     * @return
     */
    @ShellMethod("打印集合.")
    public List<String> printList(String a, String b) {
        return Arrays.asList(a, b);
    }


    /**
     * 如果对象没有 toString，那么只打印内存地址，有 toString，就打 toString 的内容，自动换行
     *
     * @return
     */
    @ShellMethod("打印 java 集合")
    public List<User> printUsers() {
        User user1 = new User();
        user1.setName("dingshichen");
        user1.setPhone("17788081249");
        user1.setAge(18);

        User user2 = new User();
        user2.setName("liyapeng");
        user2.setPhone("18044905757");
        user2.setAge(28);

        return Arrays.asList(user1, user2);
    }
}
