package cn.dsc.hibernate.test.curl;

import cn.dsc.hibernate.FastHibernateApplication;
import cn.dsc.hibernate.dao.EmpDao;
import cn.dsc.hibernate.dao.LeaveDao;
import cn.dsc.hibernate.entity.EmpEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * @author dingShiChen
 * @since 2019/9/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastHibernateApplication.class)
public class QueryTest {

	@Autowired
	private EmpDao empDao;

	@Autowired
	private LeaveDao leaveDao;

	/**
	 * 主键查询
	 */
	@Test
	public void queryById(){
		Optional<EmpEntity> optional = this.empDao.findById(15L);
		optional.ifPresent(System.out::println);
	}

	/**
	 * 条件模糊查询
	 */
	@Test
	public void query1(){
		List<EmpEntity> emps = this.empDao.findEmpEntitiesByNameLike("%alp%");
		System.out.println(emps);
	}

	/**
	 * 多条件查询
	 */
	@Test
	public void query2(){
		List<EmpEntity> emps = this.empDao.findAllBySalaryGreaterThanAndSexEquals(10000, "0");
		System.out.println("薪水超过1万的女员工有：");
		emps.forEach(System.out::println);

		List<EmpEntity> emps2 = this.empDao.findAllBySalaryGreaterThanAndSexEquals(10000, "1");
		System.out.println("薪水超过1万的男员工有：");
		emps2.forEach(System.out::println);
	}

	/**
	 * 根据sql查询
	 */
	@Test
	public void queryBySql(){
		EmpEntity emp = this.empDao.findBySql("13890726658");
		System.out.println(emp);
	}

	/**
	 * 动态条件查询
	 */
	@Test
	public void queryAuto(){
		String nameLike = "%alp%";
		String salaryGreater = "7000";
		List<EmpEntity> emps = this.empDao.findAll((Specification<EmpEntity>) (root, criteriaQuery, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (!StringUtils.isEmpty(nameLike)) {
				predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), nameLike));
			}
			if (!StringUtils.isEmpty(salaryGreater)) {
				predicate.getExpressions().add(criteriaBuilder.greaterThan(root.get("salary"), Integer.valueOf(salaryGreater)));
			}
			return predicate;
		});
		emps.forEach(System.out::println);
	}

	/**
	 * 分页查询 PageRequest
	 */
	@Test
	public void queryByPageRequest(){
		Pageable pageable = PageRequest.of(0, 5);	//springbootdata PageRequest分页是从0开始算第一页的
		Page<EmpEntity> pageInfo = this.empDao.findAll(pageable);
		System.out.println("分页查询总记录数：" + pageInfo.getTotalElements());
		System.out.println("分页查询总页数：" + pageInfo.getTotalPages());
		System.out.println("分页查询页容：" + pageInfo.getSize());
		System.out.println("分页查询当前页码：" + pageable.getPageNumber());
		System.out.println("分页查询结果：");
		pageInfo.get().forEach(System.out::println);
	}

	/**
	 * 组合条件分页查询
	 */
	@Test
	public void queryByPageWhere(){
		String nameLike = "%alp%";
		String salaryGreater = "7000";
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");	//springbootdata PageRequest分页是从0开始算第一页的
		Page<EmpEntity> pageInfo = this.empDao.findAll((Specification<EmpEntity>) (root, criteriaQuery, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (!StringUtils.isEmpty(nameLike)) {
				predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), nameLike));
			}
			if (!StringUtils.isEmpty(salaryGreater)) {
				predicate.getExpressions().add(criteriaBuilder.greaterThan(root.get("salary"), Integer.valueOf(salaryGreater)));
			}
			return predicate;
		}, pageable);
		System.out.println("分页查询总记录数：" + pageInfo.getTotalElements());
		System.out.println("分页查询总页数：" + pageInfo.getTotalPages());
		System.out.println("分页查询页容：" + pageInfo.getSize());
		System.out.println("分页查询当前页码：" + pageable.getPageNumber());
		System.out.println("分页查询结果：");
		pageInfo.get().forEach(System.out::println);
	}

	/**
	 * 关联查询
	 */
	@Test
	public void queryJoin() throws ParseException {
//		List<LeaveEmpInfo> leaveEmps = this.leaveDao.findLeaveEmps();
//		leaveEmps.forEach(System.out::println);
	}
}
