package cn.dsc.test.function;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Consumer< T >	    接收T对象，不返回值
 * Predicate< T >	    接收T对象并返回boolean
 * Function< T, R >	    接收T对象，返回R对象
 * Supplier< T >	    提供T对象（例如工厂），不接收值
 * UnaryOperator< T >	接收T对象，返回T对象
 * BiConsumer<T, U>	    接收T对象和U对象，不返回值
 * BiPredicate<T, U>	接收T对象和U对象，返回boolean
 * BiFunction<T, U, R>	接收T对象和U对象，返回R对象
 * BinaryOperator< T >	接收两个T对象，返回T对象
 * @author dingshichen
 */
public class ConsumerTest {

    private Integer size = 10;

    private void execute(Consumer<Integer> consumer, Integer num){
        consumer.accept(num);
    }

    private void executeAfter(Consumer<Integer> before, Consumer<Integer> after, Integer num){
        before.andThen(after).accept(num);
    }

    private void excute(Consumer<Integer> consumer){
        consumer.accept(size);
    }

    @Test
    public void testExecute(){
        this.excute(i -> {
            System.out.println(i ++);
            i ++;
            System.out.println(++ i);
        });
    }
}
