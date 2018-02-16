package com.sununiq.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * spring mvc对静态资源的处理，是通过chain handler的方式实现，可以增加自定义的handler来增加对资源的处理
 */
@Configuration
@EnableWebMvc
public class WebResourceConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        addAppClasspathFileMapping(registry);

        addSystemFileResourceMapping(registry);

        addFileMappingWithVersionNo(registry);
    }

    /**
     * 使用spring mvc映射系统资源
     * http:localhost:8080/files/123.txt
     * 会查找路径：/Users/sununiq/github/SSM-Examples/123.txt 的文件
     */
    private void addSystemFileResourceMapping(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:/Users/sununiq/github/SSM-Examples/");
    }

    /**
     * http:localhost:8080/classpath/123.txt
     * 会查找classpath下: static/123.txt 文件
     */
    private void addAppClasspathFileMapping(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/classpath/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 给资源的映射增加版本号 以及 是否缓存资源
     * http://localhost:8080/version/1.0/123.txt
     * 会查找classpath下: static/123.txt 文件
     */
    private void addFileMappingWithVersionNo(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("version/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false) // 是否缓存资源
                .addResolver(new VersionResourceResolver().addFixedVersionStrategy("1.0", "/**"));
    }
}