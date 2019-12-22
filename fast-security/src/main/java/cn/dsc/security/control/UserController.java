package cn.dsc.security.control;

import cn.dsc.security.common.ApiModel;
import cn.dsc.security.model.dto.UserDTO;
import cn.dsc.security.model.po.User;
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
@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ApiModel<UserDTO> home(@PathVariable Long id){
		User user = userService.findUserById(id);
		UserDTO userDTO = new UserDTO(user.getId(), user.getName());
		return ApiModel.success(userDTO);
	}
}
