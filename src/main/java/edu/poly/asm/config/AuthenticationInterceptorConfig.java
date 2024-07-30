package edu.poly.asm.config;

import edu.poly.asm.interceptor.AdminAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AdminAuthenticationInterceptor adminAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthenticationInterceptor).addPathPatterns("/admin/**");
    }
}
