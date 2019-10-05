package cn.dsc.hibernate.test.curl;

import cn.dsc.hibernate.FastHibernateApplication;
import cn.dsc.hibernate.dao.EmpDao;
import cn.dsc.hibernate.dao.LeaveDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dingShiChen
 * @since 2019/9/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastHibernateApplication.class)
public class UpdateTest {

	@Autowired
	private EmpDao empDao;

	@Autowired
	private LeaveDao leaveDao;



}
