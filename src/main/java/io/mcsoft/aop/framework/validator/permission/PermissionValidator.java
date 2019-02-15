package io.mcsoft.aop.framework.validator.permission;

import io.mcsoft.aop.framework.copied.AccountPO;
import io.mcsoft.aop.framework.copied.Permission;
import io.mcsoft.aop.framework.copied.PermissionService;
import io.mcsoft.aop.framework.validator.LoginValidator;
import io.mcsoft.aop.framework.validator.Validator;
import io.mcsoft.aop.framework.validator.permission.handler.AnnotationValidateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限校验器
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
@Component
public class PermissionValidator implements Validator {
    @Autowired
    private List<AnnotationValidateHandler> annotationValidateHandlerList = new ArrayList<>();
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean canDoSth(HttpServletRequest request, Object... params) {
        Annotation[] annotations = (Annotation[]) params;
        boolean result = true;
        AccountPO account = LoginValidator.CURRENT_ACCOUNT.get();
        if (null == account) {
            //当前没有登陆，则校验判断为失败
            return false;
        }
        //获取用户的权限列表
        List<Permission> permissions;
        permissions = permissionService.getPermissionsByAccountId(account.getAccountId());
        for (Annotation annotation : annotations) {
            //fail-fast
            if (!result) {
                break;
            }
            AnnotationValidateHandler handler;
            if (null != (handler = findValidateHandler(annotation))) {
                result &= handler.validate(account, permissions, annotation);
            }
        }

        return result;
    }

    /**
     * 找到注解对应处理实现
     */
    private AnnotationValidateHandler findValidateHandler(Annotation annotation) {
        for (AnnotationValidateHandler annotationValidateHandler : annotationValidateHandlerList) {
            if (annotationValidateHandler.getRelatedAnnotation().equals(annotation.annotationType())) {
                return annotationValidateHandler;
            }
        }
        return null;
    }
}
