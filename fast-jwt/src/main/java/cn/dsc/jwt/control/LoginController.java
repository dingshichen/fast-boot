package cn.dsc.jwt.control;

import cn.dsc.jwt.model.User;
import cn.dsc.jwt.model.UserInfo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 * @author dingShiChen
 * @since 2019/9/20
 */
@Slf4j
@RestController
public class LoginController {

	/* 加密密钥 */
	private static final String SECRET_KEY = "K5880";

	/* 过期时间30秒 */
	private static final Long EXPIRE_TIME = 30L;

	/* JWT头 */
	private static final String HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;


	/**
	 * 登陆成功后，加密返回一个token
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestBody @Valid User user){
		//1、校验账号密码

		//2、生成JWT头部
		String header = Base64.getUrlEncoder().encodeToString(HEADER.getBytes(StandardCharsets.UTF_8));
		//3、生成JWT声明部分（可以加密，验证时先解密再验签）
		UserInfo userInfo = UserInfo.builder()
				.name(user.getName())
				.gender(1)
				.expireTime(LocalDateTime.now().plusSeconds(EXPIRE_TIME))
				.build();
		String userInfoJo = JSON.toJSONString(userInfo);
		String payload = Base64.getUrlEncoder().encodeToString(userInfoJo.getBytes(StandardCharsets.UTF_8));
		//4、生成JWT签名，这里简单使用md5加密
		String sign = DigestUtils.md5DigestAsHex((header + payload + SECRET_KEY).getBytes());
		//5、返回到header里的Authorztion字段中，之后客户端每次请求，都要在头中携带token。也可以返回到cookie中自动发送，但是这样不能跨域
		this.response.setHeader(HttpHeaders.AUTHORIZATION, header + "." + payload + "." + sign);
		return "success";
	}

	/**
	 * 请求时，校验token
	 * @return
	 */
	@PostMapping("/do")
	public String doSomeThing(){
		//1、获取全token
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		//2、获取三个部分
		int index1 = token.indexOf(".");
		int index2 = token.lastIndexOf(".");
		String header = token.substring(0, index1);
		String payload = token.substring(index1 + 1, index2);
		String sign = token.substring(index2 + 1);
		//3、验签
		String signThis = DigestUtils.md5DigestAsHex((header + payload + SECRET_KEY).getBytes());
		Assert.isTrue(sign.equals(signThis), "请登陆！");
		//4、解码
		byte[] decodeBytes = Base64.getUrlDecoder().decode(payload);
		String userInfo = new String(decodeBytes, StandardCharsets.UTF_8);
		log.info("解码后的userinfo : {}", userInfo);
		UserInfo userInfo1 = JSON.parseObject(userInfo, UserInfo.class);
		//5、判断是否失效
		Assert.isTrue(userInfo1.getExpireTime().isBefore(LocalDateTime.now()), "请登录！");
		//6、生成新的Token返回给用户........

		return "success";
	}

}
