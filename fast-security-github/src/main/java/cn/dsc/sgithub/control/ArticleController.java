package cn.dsc.sgithub.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author dingShiChen
 * @since 2019/7/17
 */
@RestController
@RequestMapping("article")
public class ArticleController {

	@GetMapping("{id}")
	public String load(@PathVariable Long id) {
		return "This is my first blog";
	}

	@PostMapping("add")
	public void create(@AuthenticationPrincipal UserDetails user) {

	}
}
