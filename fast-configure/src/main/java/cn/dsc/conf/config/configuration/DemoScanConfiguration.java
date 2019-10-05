package cn.dsc.conf.config.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 原本FastConfigApplication默认扫描包路径是不包含DemoScanServiceImpl
 * 但是搭配添加@ComponentScan自动扫描注解可以扫包装配
 * @author dingShiChen
 * @since 2019/8/29
 */
@Configuration
@ComponentScan(basePackages = "cn.dsc.dev")
public class DemoScanConfiguration {

}
