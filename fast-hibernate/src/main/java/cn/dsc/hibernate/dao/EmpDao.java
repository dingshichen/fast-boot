package cn.dsc.hibernate.dao;

import cn.dsc.hibernate.entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * CrudRepository主要提供CRUD功能。
 * PagingAndSortingRepository提供了做分页和排序记录的方法。
 * JpaRepository提供了一些JPA相关的方法，例如刷新持久化上下文和批量删除记录。
 * JpaRepository继承于上面两者，具有CrudRepository和PagingAndSortingRepository的所有函数
 * JpaSpecificationExecutor 接口做多条件动态查询
 * </p>
 *
 * @author dingShiChen
 * @since 2019/9/13
 */
public interface EmpDao extends JpaRepository<EmpEntity, Long>, JpaSpecificationExecutor<EmpEntity> {

	/**
	 * 在Hibernate中，不需要具体实现，只需要方法名按照约定设计，即可以完成查询
	 * @param name
	 * @return
	 */
	List<EmpEntity> findEmpEntitiesByNameLike(String name);

	List<EmpEntity> findAllBySalaryGreaterThanAndSexEquals(Integer salary, String sex);

	@Query("from EmpEntity e where e.phone=:phone")
	EmpEntity findBySql(@Param("phone") String phone);

//	EmpEntity findByLeaveEntitie_IdAnd
}
