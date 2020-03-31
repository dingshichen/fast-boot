package cn.dsc.shardingjdbc.mapper;

import cn.dsc.shardingjdbc.entity.Member;

/**
 * @author dingshichen
 */
public interface MemberMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Integer save(String name);
}