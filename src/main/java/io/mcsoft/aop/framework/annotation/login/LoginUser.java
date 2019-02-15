package io.mcsoft.aop.framework.annotation.login;

import java.lang.annotation.*;

/**
 * 用于标记在Controller方法参数上，允许拦截器获取到用户对象后自动赋值到该参数
 *
 * Created by MC on 2019/2/11.
 *
 * @author MC
 * @date 2019/2/11
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
