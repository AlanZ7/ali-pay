package com.xxxx.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yeb
 * Swagger2配置类
 * @author: 作者
 * @create: 2021-09-13 16:20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){
     return  new Docket(DocumentationType.SWAGGER_2)
             .apiInfo(apiInfo())
             .select()
             .apis(RequestHandlerSelectors.basePackage("com.xxxx.server.controller"))
             .paths(PathSelectors.any())
             .build()
              //给swagger2令牌
             .securitySchemes(securitySchemes())   //设置请求头信息
             .securityContexts(securityContexts()) ;   //设置需要登录认证的路径  其他默认需要认证
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("云E办接口文档")
                .description("云E办描述:本项目112233")
                .contact(new Contact("xxxx","http:localhost:8081/doc.html","xxx@xx.com"))
                .version("1.0")
                .build();
    }
    private List<ApiKey> securitySchemes(){
        //设置请求头信息
        List<ApiKey> result=new ArrayList<>();
        ApiKey apiKey=new ApiKey("Authorization","Authorization","Header");
        result.add(apiKey);
        return result;
    }
    private List<SecurityContext> securityContexts(){
        //设置需要登录认证的路径
        List<SecurityContext> result=new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String s) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth()) //路径允许权限
                .forPaths(PathSelectors.regex(s))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result=new ArrayList<>();
        AuthorizationScope authorizationScope=new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }

}
