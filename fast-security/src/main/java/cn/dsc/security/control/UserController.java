package cn.dsc.security.control;

import cn.dsc.security.common.ResponseData;
import cn.dsc.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingShiChen
 * @since 2019/7/15
 */
@RequestMapping("/admin/home")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("{id}")
	public ResponseData home(@PathVariable Long id){
		return userService.getUserHome(id);
	}
}
