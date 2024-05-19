package org.springboot.login_back.config;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/20 上午1:32
 * @Description:
 **/
import org.springboot.login_back.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，并指定拦截路径和排除路径
        registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**") // 指定拦截器的拦截路径
            .excludePathPatterns("/login", "/register", "/css/**", "/js/**", "/images/**"); // 指定不拦截的路径
    }
}