package cn.dsc.fasttest.service.impl;

import cn.dsc.fasttest.mapper.RoleMapper;
import cn.dsc.fasttest.model.Role;
import cn.dsc.fasttest.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public Role findRoleById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long saveRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleMapper.insertSelective(role);
        return role.getId();
    }
}
