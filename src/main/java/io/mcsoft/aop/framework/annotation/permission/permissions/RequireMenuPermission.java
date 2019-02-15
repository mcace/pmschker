package io.mcsoft.aop.framework.annotation.permission.permissions;

import io.mcsoft.aop.framework.annotation.permission.RequirePermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单
 * Created by MC on 2019/1/24.
 *
 * @author MC
 * @date 2019/1/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@RequirePermission
public @interface RequireMenuPermission {
    /**
     * @return 菜单对应的KEY
     */
    MenuKeys[] value();

    /**
     * @return 字符串形式的KEY，如果觉得在enum中添加元素太不灵活，则可以手动将KEY的字符串赋值到注解
     */
    String[] keysString() default {};
}
