package cn.dsc.hibernate.service.impl;

import cn.dsc.hibernate.dao.EmpDao;
import cn.dsc.hibernate.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dingshichen
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Transactional
    @Override
    public void deleteByName(String name) {
        empDao.deleteByName(name);
    }
}
