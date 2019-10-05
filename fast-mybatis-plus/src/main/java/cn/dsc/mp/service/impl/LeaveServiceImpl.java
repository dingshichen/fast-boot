package cn.dsc.mp.service.impl;

import cn.dsc.mp.mapper.LeaveMapper;
import cn.dsc.mp.model.Leave;
import cn.dsc.mp.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author dingShiChen
 * @since 2019/9/1
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {
}
