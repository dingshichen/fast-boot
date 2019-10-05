package cn.dsc.faststarter.config;

import cn.dsc.faststarter.properties.WordProperties;
import cn.dsc.faststarter.service.WordService;
import cn.dsc.faststarter.service.impl.DefaultWordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/8/30
 */
@Configuration
@ConditionalOnClass(DefaultWordServiceImpl.class)
@EnableConfigurationProperties(WordProperties.class)
public class WordAutoConfiguration {

	private final WordProperties wordProperties;

	@Autowired
	public WordAutoConfiguration(WordProperties wordProperties) {
		this.wordProperties = wordProperties;
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "fast.word", value = "enabled", havingValue = "true")
	public WordService wordService(){
		return new DefaultWordServiceImpl(this.wordProperties.getPrefix(), this.wordProperties.getSuffix());
	}
}
