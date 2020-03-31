package cn.dsc.shardingjdbc.service.impl;

import cn.dsc.shardingjdbc.mapper.BrandMapper;
import cn.dsc.shardingjdbc.model.Brand;
import cn.dsc.shardingjdbc.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Integer saveNewBrand(String code) {
        return brandMapper.save(code);
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}
