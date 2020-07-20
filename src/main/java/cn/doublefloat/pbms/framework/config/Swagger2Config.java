package cn.doublefloat.pbms.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 李广帅
 * @date 2020/7/19 10:52 下午
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo());
    }


    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("失物招领系统API文档")
                .description("失物招领系统后台API文档")
                .version("1.0")
                .contact(new Contact("Double", "shop.doublefloat.cn", "203814477@qq.com"))
                .build();
    }
}