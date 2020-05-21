package cn.dsc.fasttest.conf;

import cn.dsc.fasttest.service.AppleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingshichen
 */
@Configuration
public class AppleConfiguration {

    @Bean
    AppleService appleService() {
        return () -> "apple";
    }
}
