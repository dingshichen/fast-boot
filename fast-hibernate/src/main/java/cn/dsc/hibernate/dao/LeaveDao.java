package cn.dsc.hibernate.dao;

import cn.dsc.hibernate.entity.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 *
 * </p>
 *
 * @author dingShiChen
 * @since 2019/9/13
 */
public interface LeaveDao extends JpaRepository<LeaveEntity, Long>, JpaSpecificationExecutor<LeaveEntity> {

//	@Query("select new cn.dsc.hibernate.entity.LeaveEmpInfo(l.id, l.empId, l.createDate, e.name, e.phone) from LeaveEntity l left join EmpEntity e where l.empId = e.id")
//	List<LeaveEmpInfo> findLeaveEmps();
}
