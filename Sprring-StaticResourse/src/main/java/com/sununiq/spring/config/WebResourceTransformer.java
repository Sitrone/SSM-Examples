package com.sununiq.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.CssLinkResourceTransformer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

/**
 * spring mvc 支持对资源的修改，提供了四个transformer
 * <p>
 * AppCacheManifestTransformer: 帮助处理 HTML5 离线应用的 AppCache 清单内的文件
 * CachingResourceTransformer: 缓存其它 transfomer 的结果，作用同 CachingResourceResolver
 * CssLinkResourceTransformer: 处理 css 文件中的链接，为其加上版本号
 * ResourceTransformerSupport: 抽象类，自定义 transfomer 时继承
 *
 * Ref：https://blog.coding.net/blog/spring-static-resource-process
 */
@EnableWebMvc
@Configuration
public class WebResourceTransformer extends WebMvcConfigurerAdapter {

    /**
     * 会将 css 文件中的 @import 或 url() 函数中的资源路径自动转换为包含版本号的路径。
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"))
                .addTransformer(new CssLinkResourceTransformer());

    }
}
