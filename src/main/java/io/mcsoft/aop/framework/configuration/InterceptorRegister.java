package io.mcsoft.aop.framework.configuration;

import io.mcsoft.aop.framework.interceptor.LoginInterceptor;
import io.mcsoft.aop.framework.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 拦截器注册器，把interceptor包里的拦截器进行注册的工具
 * 只要继承自WebMvcConfigurerAdapter，Spring就会自动应用其中的addInterceptors方法
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
@Configuration
public class InterceptorRegister extends WebMvcConfigurerAdapter {
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();


        registry.addInterceptor(loginInterceptor);
        registry.addInterceptor(permissionInterceptor);

        super.addInterceptors(registry);
    }
}
