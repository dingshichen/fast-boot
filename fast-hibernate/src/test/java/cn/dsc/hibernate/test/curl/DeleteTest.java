package cn.dsc.hibernate.test.curl;

import cn.dsc.hibernate.FastHibernateApplication;
import cn.dsc.hibernate.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastHibernateApplication.class)
public class DeleteTest {

    @Autowired
    private EmpService empService;

    /**
     * 根据名称删除员工
     * 还特么必须在有事务的情况下才能运行，不然会报错
     * 从sql的打印上看，它是先通过name查询，查询到之后才根据返回的Id去删除。可能执行了多次sql。
     */
    @Test
    public void delete() {
        empService.deleteByName("dingshichen");
    }
}
