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
import java.util.List;

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

	/**
	 * 新增一条记录
	 * 配合实体类上的 @DynamicInsert 注解，如果某个字段没有赋值，则使用数据库的默认值。
	 * 使用数据库自增主键，可以返回这条数据的主键
	 * @throws ParseException
	 */
	@Test
	public void add() throws ParseException {
		EmpEntity emp = EmpEntity.builder().name("dingshichen").sex("男").phone("15987990489").salary(6770).birthday(DateUtil.stringToDate("1995-02-21")).build();
		EmpEntity result = empDao.save(emp);
		System.out.println("新增员工成功，返回主键：" + result.getId());
	}

	/**
	 * 批量新增，循环插入，不是批插入SQL
	 * 返回多条数据的主键
	 * @throws ParseException
	 */
	@Test
	public void addAll() throws ParseException {
		EmpEntity emp1 = EmpEntity.builder().name("clawer").sex("女").phone("17890657811").salary(12890).birthday(DateUtil.stringToDate("1991-11-21")).build();
		EmpEntity emp2 = EmpEntity.builder().name("soulket").sex("男").phone("13890726658").salary(6788).birthday(DateUtil.stringToDate("1988-10-01")).build();
		EmpEntity emp3 = EmpEntity.builder().name("angluer").sex("女").phone("18806784200").salary(4900).birthday(DateUtil.stringToDate("1981-02-17")).build();
		List<EmpEntity> result = empDao.saveAll(Arrays.asList(emp1, emp2, emp3));
		System.out.println("批量插入成功，返回主键：");
		result.forEach(e -> System.out.println(e.getId()));
	}
}
