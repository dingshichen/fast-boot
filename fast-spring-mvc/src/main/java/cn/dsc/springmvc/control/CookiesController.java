package cn.dsc.springmvc.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dingShiChen
 * @since 2019/11/2
 */
@Slf4j
@RestController
public class CookiesController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@GetMapping("/createCookies")
	public String createCookies(String k, String v){
		Cookie cookie = new Cookie(k, v);
		this.response.addCookie(cookie);
		return "success";
	}

	@GetMapping("/readCookies")
	public String readCookies(String k){
		for (Cookie cookie : this.request.getCookies()) {
			if (k.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return "error";
	}
}
