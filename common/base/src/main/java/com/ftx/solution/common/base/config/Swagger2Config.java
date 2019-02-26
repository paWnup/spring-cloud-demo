package com.ftx.solution.common.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger接口文档配置
 *
 * @author puan
 * @date 2018-11-13 14:34
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 扫描包路径
     */
    @Value("${swagger.scan.basePackage}")
    private String basePackage;

    /**
     * API文档标题
     */
    @Value("${swagger.apiInfo.title}")
    private String title;

    /**
     * API文档创建人
     */
    @Value("${swagger.apiInfo.contact}")
    private String contact;

    /**
     * API文档版本
     */
    @Value("${swagger.apiInfo.version}")
    private String version;

    /**
     * API文档描述
     */
    @Value("${swagger.apiInfo.description}")
    private String description;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(title)
                //创建人
                .contact(new Contact(contact, "", ""))
                //版本号
                .version(version)
                //描述
                .description(description)
                .build();
    }

}
