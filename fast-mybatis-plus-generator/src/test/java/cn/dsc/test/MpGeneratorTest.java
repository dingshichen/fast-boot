package cn.dsc.test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dingShiChen
 * @since 2019/9/9
 */
public class MpGeneratorTest {

	@Test
	public void generateCode() {
		generate("wx_public");
	}

	private void generate(String... tableNamesInclude){
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
		gc.setOutputDir("D:\\迅雷下载");
		gc.setAuthor("dingshichen");
		gc.setOpen(false);
		//默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
		gc.setFileOverride(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/wechat_mall?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
//		pc.setModuleName(moduleName);
		pc.setParent("cn.dsc.mpg");
		pc.setXml("mybatis");
		mpg.setPackageInfo(pc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setInclude(tableNamesInclude);
		strategy.setSuperEntityColumns("id");
		strategy.setRestControllerStyle(true);
		strategy.setEntityTableFieldAnnotationEnable(true);
		strategy.setControllerMappingHyphenStyle(true);
//		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
//		mpg.setTemplateEngine(new VelocityTemplateEngine());

//		configCustomizedCodeTemplate(mpg);
//		configInjection(mpg);

		mpg.execute();
	}

	/**
	 * 自定义模板
	 * @param mpg
	 */
	private void configCustomizedCodeTemplate(AutoGenerator mpg){
		//配置 自定义模板
		TemplateConfig templateConfig = new TemplateConfig()
				.setEntity("templates/MyEntityTemplate.java")//指定Entity生成使用自定义模板
				.setXml(null);//不生成xml
		mpg.setTemplate(templateConfig);
	}

	/**
	 * 配置自定义参数/属性
	 *
	 * @param mpg
	 */
	private void configInjection(AutoGenerator mpg){
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
                /*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 */
			}
		};
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/MyEntityTemplate.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 指定模板生，自定义生成文件到哪个地方
//                return "D:/abc";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
	}
}
