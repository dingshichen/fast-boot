package cn.dsc.security.service.impl;

import cn.dsc.security.mapper.UserMapper;
import cn.dsc.security.model.po.User;
import cn.dsc.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author dingShiChen
 * @since 2019/7/15
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return (UserDetails) userMapper.selectByName(s);
	}

	@Override
	public User findUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
    public Long createUser(String name, String pwd) {
		User user = new User();
		user.setName(name);
		user.setPwd(passwordEncoder.encode(pwd));
		userMapper.insertSelective(user);
		return user.getId();
    }
}
