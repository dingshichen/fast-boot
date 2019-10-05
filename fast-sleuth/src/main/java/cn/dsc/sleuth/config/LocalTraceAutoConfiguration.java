package cn.dsc.sleuth.config;

import brave.Tracing;
import cn.dsc.sleuth.config.impl.DefaultTraceServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingShiChen
 * @since 2019/8/28
 */
@Configuration
@AutoConfigureAfter(TraceAutoConfiguration.class)
@ConditionalOnProperty(value = "spring.sleuth.enabled", matchIfMissing = true)
public class LocalTraceAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public TraceService traceService(Tracing tracing){
		return new DefaultTraceServiceImpl(tracing);
	}

}
