package cn.dsc.shardingjdbc.mapper;

import cn.dsc.shardingjdbc.model.Brand;

/**
 * @author dingshichen
 */
public interface BrandMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}