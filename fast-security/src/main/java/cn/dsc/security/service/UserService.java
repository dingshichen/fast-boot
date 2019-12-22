package cn.dsc.security.service;

import cn.dsc.security.model.po.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dingShiChen
 * @since 2019/7/15
 */
public interface UserService extends UserDetailsService {


	User findUserById(Long id);

	/**
	 * 创建用户
	 * @param name
	 * @param pwd
	 */
    Long createUser(String name, String pwd);
}
