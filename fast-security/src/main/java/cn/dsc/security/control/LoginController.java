package cn.dsc.security.control;

import cn.dsc.security.common.ResponseData;
import cn.dsc.security.model.vo.RegistVO;
import cn.dsc.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseData regist(@RequestBody RegistVO vo){
		return userService.regist(vo);
	}
}
