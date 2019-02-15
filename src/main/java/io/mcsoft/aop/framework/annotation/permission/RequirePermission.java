package io.mcsoft.aop.framework.annotation.permission;


import io.mcsoft.aop.framework.annotation.login.RequireLogin;

import java.lang.annotation.*;

/**
 * 用于判断方法、类是否需要验证权限，后续的权限注解都需要结合它
 * 1. 只能使用在权限注解上
 * 2. 需要用户登陆
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
@RequireLogin
@Inherited
public @interface RequirePermission {
}
