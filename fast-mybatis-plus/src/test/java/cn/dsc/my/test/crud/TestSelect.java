package cn.dsc.my.test.crud;

import cn.dsc.mp.MybatisPlusApplication;
import cn.dsc.mp.constant.Sex;
import cn.dsc.mp.mapper.EmpMapper;
import cn.dsc.mp.mapper.LeaveMapper;
import cn.dsc.mp.model.Emp;
import cn.dsc.mp.model.Leave;
import cn.dsc.mp.model.LeaveEmpVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class TestSelect {

	@Autowired
	private EmpMapper empMapper;
	@Autowired
	private LeaveMapper leaveMapper;

	/**
	 * 普通查询
	 */
	@Test
	public void selectByLambdaWrappers1(){
		Emp emp = empMapper.selectOne(Wrappers.<Emp>lambdaQuery().eq(Emp::getId, 1L));
		System.out.println("简单查询单个对象 : " + emp);

		List<Emp> emps1 = empMapper.selectList(Wrappers.<Emp>lambdaQuery().gt(Emp::getSalary, 5000));
		System.out.println("简单查询 : " + emps1);

		List<Emp> emps2 = empMapper.selectList(Wrappers.<Emp>lambdaQuery().like(Emp::getName, "al").eq(Emp::getSex, Sex.MAN));
		System.out.println("多条件查询 : " + emps2);

		LambdaQueryWrapper<Emp> wrapper = Wrappers.lambdaQuery();
		wrapper.eq(Emp::getName, "sour").eq(Emp::getPhone, "18200862911");
		List<Emp> emps3 = empMapper.selectList(wrapper);
		System.out.println("换一种写法" + emps3);
	}

	/**
	 * 自定义sql写法
	 */
	@Test
	public void selectBySql(){
		LambdaQueryWrapper<Emp> wrapper = Wrappers.lambdaQuery();
		wrapper.apply("salary > {0}", "10000");
		List<Emp> emps = empMapper.selectList(wrapper);
		System.out.println("apply sql 注入 : " + emps);

		List<Emp> emps1 = empMapper.queryBySql(Wrappers.<Emp>lambdaQuery().eq(Emp::getSex, Sex.MAN));
		System.out.println("注解实现自定义sql : " + emps1);
	}

	/**
	 * 只查询某些字段
	 */
	@Test
	public void selectByQueryWrappers1(){
		QueryWrapper<Emp> query = Wrappers.query();
		query.select("id", "name", "phone");
		query.eq("id", "2");
		Emp emp = empMapper.selectOne(query);
		System.out.println(emp);
	}

	/**
	 * 查询表名是关键字的表，需要用``转义
	 * last是拼接条件
	 */
	@Test
	public void selectLeave(){
		Leave leave = leaveMapper.selectOne(Wrappers.<Leave>query().select("id", "emp_id", "create_date").last("limit 1"));
		System.out.println("last拼接limit查询结果 : " + leave);
	}

	/**
	 * 关联查询
	 */
	@Test
	public void selectJoin(){
		List<LeaveEmpVO> leaveEmpVOS = leaveMapper.queryJoinEmp("");
		leaveEmpVOS.forEach(System.out::println);

		System.out.println("---------");

		List<LeaveEmpVO> leaveEmpVOS1 = leaveMapper.queryJoinEmp("and l.id < 4");
		leaveEmpVOS1.forEach(System.out::println);
	}

	/**
	 * 分页查询
	 */
	@Test
	public void selectPage(){
		Page<Emp> page = new Page<>();
		page.setCurrent(2);		//设置页码
		page.setSize(2);		//设置页容
		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
		// page.setOptimizeCountSql(false);
		// 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
		// 要点!! 分页返回的对象与传入的对象是同一个
		IPage<Emp> empIPage = this.empMapper.selectPage(page, Wrappers.lambdaQuery());
		System.out.println("分页查询总记录数：" + empIPage.getTotal());	//当前条件总记录数
		System.out.println("分页查询总页数：" + empIPage.getPages());	//当前条件总页数
		System.out.println("分页查询当前页码：" + empIPage.getCurrent());	//当前页码
		System.out.println("分页查询得到数据：");
		empIPage.getRecords().forEach(System.out::println);
	}

}