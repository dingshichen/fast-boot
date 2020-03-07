package cn.dsc.shardingjdbc.service;

import cn.dsc.shardingjdbc.model.Brand;

/**
 * @author dingshichen
 */
public interface BrandService {

    Integer saveNewBrand(String code);

    Brand findById(Integer id);
}
