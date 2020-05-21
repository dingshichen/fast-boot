package cn.dsc.fasttest.conf;

import cn.dsc.fasttest.service.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingshichen
 */
@Configuration
public class FastConfiguration {

    @Bean
    public ConfigService configService() {
        return () -> "hello people";
    }
}
