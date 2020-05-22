package cn.dsc.fasttest.sql;

import cn.dsc.fasttest.FastTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dingshichen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastTestApplication.class)
public class SqlRoleTest {

    /**
     * 方法执行前执行脚本，加上事务注解可以回滚
     */
    @Transactional
    @Sql("classpath:/cn/dsc/fasttest/sql/role.sql")
    @Test
    public void testSql() {
        System.out.println("*************** test sql ***************");
    }


}
