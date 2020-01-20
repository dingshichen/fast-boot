package cn.dsc.test.pvolatile;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 丁时辰
 */
public class SingleVolatile {

    @Test
    public void noVolatileTest() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 1L,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        Runnable r = () -> {
            SingleOne instance = null;
            try {
                instance = SingleOne.getInstance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(instance);
        };
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        Thread.sleep(5000L);
    }

    static class SingleOne {

        private static SingleOne singleOne;

        private SingleOne() {
        }

        static SingleOne getInstance() throws InterruptedException {
            Thread.sleep(1000L);
            if (singleOne == null) {
                synchronized (SingleOne.class) {
                    if (singleOne == null) {
                        singleOne = new SingleOne();
                    }
                }
            }
            return singleOne;
        }
    }
}
