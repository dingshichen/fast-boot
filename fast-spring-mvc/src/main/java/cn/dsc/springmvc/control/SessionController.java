package cn.dsc.springmvc.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Slf4j
@RestController
public class SessionController {

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/createSession")
	public String createSession(String k, String v){
		this.httpSession.setAttribute(k, v);
		return "success";
	}

	@GetMapping("/readSession")
	public String readSession(String k){
		return (String) this.httpSession.getAttribute(k);
	}

	@GetMapping("/deleteSession")
	public String deleteSession(String k){
		this.httpSession.removeAttribute(k);
		return "success";
	}

	@GetMapping("/invalidateSession")
	public String invalidateSession(){
		this.httpSession.invalidate();
		return "success";
	}
}
