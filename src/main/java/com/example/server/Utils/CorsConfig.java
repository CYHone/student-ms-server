package com.example.server.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }
}



//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/admin/**") // 配置允许跨源访问的 URL pattern
////                .allowedOrigins("http://192.168.30.128") // 允许的源
////                //.allowedOrigins("*")
////                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的 HTTP 方法
////                .allowedHeaders("*") // 允许的请求头
////                .allowCredentials(true); // 允许携带认证信息（例如 cookies）
////        registry.addMapping("/teacher/**")
////                .allowedOrigins("http://192.168.30.128") // 允许的源
////                //.allowedOrigins("*")
////                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的 HTTP 方法
////                .allowedHeaders("*") // 允许的请求头
////                .allowCredentials(true);
////        registry.addMapping("/student/**")
////                .allowedOrigins("http://192.168.30.128") // 允许的源
////               // .allowedOrigins("*")
////                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的 HTTP 方法
////                .allowedHeaders("*") // 允许的请求头
////                .allowCredentials(true); // 允许携带认证信息（例如 cookies）
////    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        // 允许所有来源（根据实际情况调整）
//        config.addAllowedOrigin("*");
//        // 允许所有请求头
//        config.addAllowedHeader("*");
//        // 允许所有方法
//        config.addAllowedMethod("*");
//        // 允许凭证（cookies）
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // 对所有路径应用 CORS 配置
//
//        return new CorsFilter();
//    }
//}
