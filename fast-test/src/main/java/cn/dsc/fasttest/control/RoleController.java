package cn.dsc.fasttest.control;

import cn.dsc.fasttest.service.RoleService;
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

    private final RoleService roleService;

    @GetMapping("/{id}")
    public String role(@PathVariable("id") Long id){
        return this.roleService.findRoleById(id).getName();
    }

}
