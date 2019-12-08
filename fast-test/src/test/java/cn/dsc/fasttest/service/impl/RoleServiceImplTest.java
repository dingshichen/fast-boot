package cn.dsc.fasttest.service.impl;

import cn.dsc.fasttest.FastTestApplication;
import cn.dsc.fasttest.model.Role;
import cn.dsc.fasttest.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库的测试
 */
@Slf4j
@Transactional      //测试数据最终不提交，不会污染数据库，但是新增数据的话会递增主键
/*
 * @runWith注解作用：
    @RunWith就是一个运行器
    @RunWith(JUnit4.class)就是指用JUnit4来运行
    @RunWith(SpringJUnit4ClassRunner.class)，让测试运行于Spring测试环 境，以便在测试开始的时候自动创建Spring的应用上下文
    @RunWith(Suite.class)的话就是一套测试集合
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FastTestApplication.class)         //获取启动类、加载配置，确定装载Spring Boot
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @BeforeClass
    public static void start(){
        log.info("********* 测试类启动 *********");
    }

    @AfterClass
    public static void end(){
        log.info("********* 测试类结束 *********");
    }

    @Before
    public void setUp() throws Exception {
        log.info("测试方法running");
    }

    @After
    public void tearDown() throws Exception {
        log.info("测试方法ending");
    }

    @Test
    public void findRoleById() {
        Role role = roleService.findRoleById(2L);
        Assert.assertEquals("测试失败", role.getName(), "超级管理员");
    }

    @Test
    public void saveRole() {
        Long id = roleService.saveRole("测试管理员者");
        Assert.assertTrue("新增失败", id > 0L);
    }
}