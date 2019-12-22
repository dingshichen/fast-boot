package cn.dsc.security.control;

import cn.dsc.security.common.ApiModel;
import cn.dsc.security.model.vo.RegistVO;
import cn.dsc.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author dingShiChen
 * @since 2019/7/15
 */
@Slf4j
@RestController
@RequestMapping("/portal")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/regist")
	public ApiModel<String> regist(@RequestBody RegistVO vo){
		UserDetails user = userService.loadUserByUsername(vo.getName());
		Assert.isNull(user, "用户名已存在");
		userService.createUser(vo.getName(), vo.getPwd());
		return ApiModel.success();
	}
}
