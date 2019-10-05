package cn.dsc.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Security需要用到一个实现了AccessDecisionManager接口的类
 * 类能：根据当前用户的信息，和目标url涉及到的权限，判断用户是否可以访问
 * 判断规则：用户只要匹配到目标url权限中的一个role就可以访问
 * @author dingShiChen
 * @since 2019/7/15
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
		//todo
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
