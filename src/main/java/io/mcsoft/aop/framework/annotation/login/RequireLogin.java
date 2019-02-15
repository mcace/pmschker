package io.mcsoft.aop.framework.annotation.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 要求接口必须登录
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequireLogin {
}
