package cn.dsc.security.service.impl;

import cn.dsc.security.common.ResponseData;
import cn.dsc.security.mapper.UserMapper;
import cn.dsc.security.model.po.User;
import cn.dsc.security.model.po.UserExample;
import cn.dsc.security.model.vo.RegistVO;
import cn.dsc.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
		Assert.hasText(s, "请输入正确的账号或密码");
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(s);
		List<User> users = userMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(users)){
			return null;
		}
		return (UserDetails) users.get(0);
	}

	@Override
	public ResponseData regist(RegistVO vo) {
		User user = User.builder().name(vo.getName()).phone(vo.getPhone()).pwd(passwordEncoder.encode(vo.getPwd())).build();
		int id = userMapper.insertSelective(user);
		return ResponseData.success(id);
	}

	@Override
	public ResponseData getUserHome(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		return ResponseData.success(user);
	}
}
