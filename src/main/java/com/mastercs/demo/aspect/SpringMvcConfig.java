package com.mastercs.demo.aspect;



import com.mastercs.demo.interceptor.LoginInterceptor;
import com.mastercs.demo.interceptor.PermissionInterceptor;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    @Resource
    PermissionInterceptor permissionInterceptor;


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //允许直接访问的接口
                .excludePathPatterns(
                        "/users/login",
                        "/users/signup",
                        "/result",
                        "/tutorial",
                        "/view",
                        "/view**",
                        "/uploads",
                        "/queryById**"
                );
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/users/admin/quiz/**");
    }

    //允许图片访问路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置图片静态资源处理器
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + "/xxx/xxx");
    }


}
