package cn.dsc.security.service;

import cn.dsc.security.common.ResponseData;
import cn.dsc.security.model.vo.RegistVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dingShiChen
 * @since 2019/7/15
 */
public interface UserService extends UserDetailsService {
	ResponseData regist(RegistVO vo);

	ResponseData getUserHome(Long id);
}
