package cn.dsc.hibernate.test.curl;

import cn.dsc.hibernate.FastHibernateApplication;
import cn.dsc.hibernate.dao.EmpDao;
import cn.dsc.hibernate.entity.EmpEntity;
import cn.dsc.hibernate.test.curl.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author dingShiChen
 * @since 2019/9/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastHibernateApplication.class)
public class CreateTest {

	@Autowired
	private EmpDao empDao;

	@Test
	public void add() throws ParseException {
		EmpEntity emp = EmpEntity.builder().name("urbest").sex("1").phone("15987990489").salary(6770).birthday(DateUtil.stringToDate("1995-02-21")).build();
		EmpEntity result = empDao.save(emp);
		System.out.println("新增员工成功，返回主键：" + result.getId());
	}

	@Test
	public void addAll() throws ParseException {
		EmpEntity emp1 = EmpEntity.builder().name("clawer").sex("0").phone("17890657811").salary(12890).birthday(DateUtil.stringToDate("1991-11-21")).build();
		EmpEntity emp2 = EmpEntity.builder().name("soulket").sex("1").phone("13890726658").salary(6788).birthday(DateUtil.stringToDate("1988-10-01")).build();
		EmpEntity emp3 = EmpEntity.builder().name("angluer").sex("0").phone("18806784200").salary(4900).birthday(DateUtil.stringToDate("1981-02-17")).build();
		empDao.saveAll(Arrays.asList(emp1, emp2, emp3));
		System.out.println("批量插入成功");
	}
}
