package cn.dsc.springsession.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author dingShiChen
 * @since 2019/9/17
 */
@RestController
public class LoginController {

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/login")
	public String login(String username, String pwd){
		httpSession.setAttribute("username", username);
		httpSession.setAttribute("pwd", pwd);
		return httpSession.getId();
	}

	@GetMapping("user")
	public String user(){
		return (String) httpSession.getAttribute("username");
	}

	/**
	 * 清除session整个会话
	 * @return
	 */
	@GetMapping("rm")
	public String rm(){
		httpSession.invalidate();
		return "success";
	}
}
