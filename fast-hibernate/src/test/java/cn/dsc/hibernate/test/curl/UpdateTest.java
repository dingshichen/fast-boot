package cn.dsc.hibernate.test.curl;

import cn.dsc.hibernate.FastHibernateApplication;
import cn.dsc.hibernate.dao.EmpDao;
import cn.dsc.hibernate.dao.LeaveDao;
import cn.dsc.hibernate.entity.EmpEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

	/**
	 * 更新操作
	 * 实际上，先根据条件查询出集合，然后再对集合里的多个数据去根据主键分别查询，最后才分别根据主键update更新
	 * 注意的是，hibernate会比对查询出的对象和更新的对象，它只更新发生变化的字段
	 */
	@Test
	public void update() {
		List<EmpEntity> emp = empDao.findAllBySalaryGreaterThanAndSexEquals(5000, "男");
		emp.forEach(e -> e.setSalary(e.getSalary() - 2000));
		empDao.saveAll(emp);
	}
}
