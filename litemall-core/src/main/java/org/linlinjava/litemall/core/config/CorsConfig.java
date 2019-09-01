package org.linlinjava.litemall.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig {
    // 当前跨域请求最大有效时长。这里默认30天
    private long maxAge = 30 * 24 * 60 * 60;

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        List<String> origins = new ArrayList<>();
        origins.add("http://litemall.lidaoyuan.top");
        origins.add("http://localhost:9527");
        corsConfiguration.setAllowedOrigins(origins);
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        List<String> methods = new ArrayList<>();
        methods.add("POST");
        methods.add("GET");
        methods.add("PATCH");
        methods.add("DELETE");
        methods.add("PUT");
        methods.add("OPTIONS");
        corsConfiguration.setAllowedMethods(methods);
        corsConfiguration.setMaxAge(maxAge);
        corsConfiguration.setAllowCredentials(true);

        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
