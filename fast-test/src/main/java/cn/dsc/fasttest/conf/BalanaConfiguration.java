package cn.dsc.fasttest.conf;

import cn.dsc.fasttest.service.BalanaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingshichen
 */
@Configuration
public class BalanaConfiguration {

    @Bean
    BalanaService balanaService() {
        return () -> "balana";
    }
}
