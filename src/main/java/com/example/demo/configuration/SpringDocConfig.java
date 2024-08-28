package com.example.demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // 配置接口文档基本信息
                .info(this.getApiInfo());
    }

    private Info getApiInfo() {
        return new Info()
                // 配置文档标题
                .title("登录系统")
                // 配置文档描述
                .description("")
                // 配置作者信息
                .contact(new Contact().name("程序员小P").url("").email("1888888888@163.com"))
                // 配置License许可证信息
                .license(new License().name("Apache 2.0").url(""))
                .summary("")
                .termsOfService("")
                // 配置版本号
                .version("1.0");
    }
}