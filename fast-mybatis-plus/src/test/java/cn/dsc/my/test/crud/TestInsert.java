package cn.dsc.my.test.crud;

import cn.dsc.mp.MybatisPlusApplication;
import cn.dsc.mp.constant.Sex;
import cn.dsc.mp.mapper.EmpMapper;
import cn.dsc.mp.model.Emp;
import cn.dsc.mp.service.EmpService;
import cn.dsc.my.test.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class TestInsert {

	@Autowired
	private EmpMapper empMapper;
	@Autowired
	private EmpService empService;

	@Test
	public void insert() throws ParseException {
		Emp emp = Emp.builder().name("lisa")
				.phone("17729081567")
				.sex(Sex.WOMAN)
				.salary(5200)
				.birthday(DateUtil.stringToDate("1993-07-16"))
				.build();
		System.out.println("新增前 emp : " + emp);
		int id = empMapper.insert(emp);
		System.out.println("新增后 emp : " + emp);
		System.out.println("新增员工成功 " + id);
	}

	@Test
	public void insertBatch() throws ParseException {
		Emp emp1 = Emp.builder().name("sour").phone("18200862911").sex(Sex.MAN).salary(4890).birthday(DateUtil.stringToDate("1991-12-06")).build();
		Emp emp2 = Emp.builder().name("walper").phone("13780456688").sex(Sex.WOMAN).salary(7500).birthday(DateUtil.stringToDate("1994-02-11")).build();
		Emp emp3 = Emp.builder().name("cico").phone("15908114209").sex(Sex.WOMAN).salary(12000).birthday(DateUtil.stringToDate("1993-03-09")).build();
		Emp emp4 = Emp.builder().name("balive").phone("18845883298").sex(Sex.MAN).salary(3800).birthday(DateUtil.stringToDate("1990-11-02")).build();
		List<Emp> emps = Arrays.asList(emp1, emp2, emp3, emp4);
		boolean result = empService.saveBatch(emps);
		System.out.println("批量插入 " + result);
	}
}
