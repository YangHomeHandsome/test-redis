package com.itmayiedu.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by YJJ on 2019/9/27.
 */
//声明这是一个配置类
@Configuration
public class MVCInterceptorConfig implements WebMvcConfigurer {
    //自动注入一个配置器
    @Autowired
    MyInterceptor myInterceptor;


    //重写添加拦截器方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //以前缀配置
        registry.addInterceptor(myInterceptor).addPathPatterns("/testInterceptor","/addUser2");

        //registry.addInterceptor(myInterceptor).pathMatcher("某个准确的路径");
        //也可以配置不拦截的路径
        //registry.addInterceptor(myInterceptor).excludePathPatterns("/res/**", "/page/**");
    }
}
