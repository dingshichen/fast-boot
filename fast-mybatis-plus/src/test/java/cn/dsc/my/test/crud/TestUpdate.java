package cn.dsc.my.test.crud;

import cn.dsc.mp.MybatisPlusApplication;
import cn.dsc.mp.mapper.EmpMapper;
import cn.dsc.mp.model.Emp;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 所有update，都是更新参数实体对象中不为Null的字段
 * @author dingShiChen
 * @since 2019/9/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class TestUpdate {

	@Autowired
	private EmpMapper empMapper;

	@Test
	public void updateById(){
		Emp emp = Emp.builder().id(2L).phone("13013045678").build();
		int count = empMapper.updateById(emp);
		System.out.println("更新成功：" + count);
	}

	@Test
	public void updateByWhere(){
		Emp emp = Emp.builder().phone("17705505755").build();
		int count = empMapper.update(emp, Wrappers.<Emp>lambdaQuery().eq(Emp::getName, "lisa"));
		System.out.println("更新成功：" + count);
	}
}
