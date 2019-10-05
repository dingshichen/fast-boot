package cn.dsc.mp.mapper;

import cn.dsc.mp.model.Leave;
import cn.dsc.mp.model.LeaveEmpVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/9/1
 */
public interface LeaveMapper extends BaseMapper<Leave> {

	/**
	 * 分页查询，动态条件
	 * 使用$会有sql注入的风险，注意控制参数的来源
	 * @param sql
	 * @return
	 */
	@Select("select l.id, e.name emp_name, e.PHONE emp_phone, l.CREATE_DATE from `leave` l left join emp e on l.EMP_ID = e.ID where 1 = 1 ${sql}")
	List<LeaveEmpVO> queryJoinEmp(@Param("sql") String sql);
}
