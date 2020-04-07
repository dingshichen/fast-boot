package cn.dsc.p6spy.mapper;

import cn.dsc.p6spy.entity.Role;

/**
 *
 * @author dingshichen
 */
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}