package cn.dsc.fasttest.service;

import cn.dsc.fasttest.model.Role;

/**
 * @author dingshichen
 */
public interface RoleService {

    Role findRoleById(Long id);

    Long saveRole(String name);
}
