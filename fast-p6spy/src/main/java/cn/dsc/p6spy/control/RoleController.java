package cn.dsc.p6spy.control;

import cn.dsc.p6spy.entity.Role;
import cn.dsc.p6spy.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingshichen
 */
@RequestMapping("/role")
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleMapper roleMapper;

    @GetMapping("/{id}")
    public Role role(@PathVariable Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
