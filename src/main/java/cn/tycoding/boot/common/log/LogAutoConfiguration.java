package cn.tycoding.boot.common.log;

import cn.tycoding.boot.common.log.aspect.ApiLogAspect;
import cn.tycoding.boot.common.log.event.LogListener;
import cn.tycoding.boot.common.log.props.LogProperties;
import cn.tycoding.boot.modules.system.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2020/10/31
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication
@EnableConfigurationProperties({LogProperties.class})
public class LogAutoConfiguration {

    @Bean
    public LogListener logListener(SysLogService sysLogService) {
        return new LogListener(sysLogService);
    }

    @Bean
    public ApiLogAspect logAspect() {
        return new ApiLogAspect();
    }
}
