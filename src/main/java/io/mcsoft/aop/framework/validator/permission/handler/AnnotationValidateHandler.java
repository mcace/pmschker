package io.mcsoft.aop.framework.validator.permission.handler;

import io.mcsoft.aop.framework.copied.AccountPO;
import io.mcsoft.aop.framework.copied.Permission;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 权限注解校验处理类接口
 * 每个处理类实现用于处理特定的权限接口匹配逻辑
 * 再增加权限接口时，实现新的对应的AnnotationHandler即可
 * Created by MC on 2019/2/11.
 *
 * @author MC
 * @date 2019/2/11
 */
public interface AnnotationValidateHandler {
    /**
     * 获取与Handler关联的注解类
     */
    Class getRelatedAnnotation();

    /**
     * 通过注解的数据来处理校验
     */
    boolean validate(AccountPO account, List<Permission> permissionList, Annotation annotation);
}
