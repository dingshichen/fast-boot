package cn.dsc.fasttest.profile;

import cn.dsc.fasttest.base.BaseTest;
import cn.dsc.fasttest.model.Role;
import cn.dsc.fasttest.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author dingshichen
 */
@Slf4j
@ActiveProfiles("prod")     //指定当前生效的配置文件
public class ApplicationProfileTest extends BaseTest {

    @Value("${appprofile}")
    private String appprofile;

    @Autowired
    private RoleService roleService;

    @Test
    public void profile(){
        Role role = roleService.findRoleById(2L);
        log.info("获取到值为：{}-{}", appprofile, role.getName());
    }
}
