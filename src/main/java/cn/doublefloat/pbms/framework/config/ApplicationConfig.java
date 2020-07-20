package cn.doublefloat.pbms.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李广帅
 * @date 2020/7/19 10:32 下午
 */
@Configuration
@MapperScan("cn/doublefloat/pbms/project/**/mapper")
public class ApplicationConfig {
}
