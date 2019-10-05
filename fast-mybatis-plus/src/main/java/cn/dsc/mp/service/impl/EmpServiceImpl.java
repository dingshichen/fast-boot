package cn.dsc.mp.service.impl;

import cn.dsc.mp.mapper.EmpMapper;
import cn.dsc.mp.model.Emp;
import cn.dsc.mp.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author dingShiChen
 * @since 2019/8/31
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

}
