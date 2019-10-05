package cn.dsc.mp.mapper;

import cn.dsc.mp.model.Emp;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
public interface EmpMapper extends BaseMapper<Emp> {

	@Select("select id from emp ${ew.customSqlSegment}")
	List<Emp> queryBySql(@Param(Constants.WRAPPER) Wrapper wrapper);
}
