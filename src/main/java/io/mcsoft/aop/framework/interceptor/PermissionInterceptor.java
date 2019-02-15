package io.mcsoft.aop.framework.interceptor;

import io.mcsoft.aop.framework.annotation.permission.RequirePermission;
import io.mcsoft.aop.framework.util.AnnotationUtil;
import io.mcsoft.aop.framework.util.ResponseProcessor;
import io.mcsoft.aop.framework.copied.ResponseUtils;
import io.mcsoft.aop.framework.validator.permission.PermissionValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限拦截器，在调用Controller前验证用户权限
 * Created by MC on 2019/1/24.
 *
 * @author MC
 * @date 2019/1/24
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Resource
    private PermissionValidator permissionValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            boolean hasPermission = true;
            if (AnnotationUtil.testAnnotation((HandlerMethod) handler, RequirePermission.class)) {
                Annotation[] methodAnnotations = ((HandlerMethod) handler).getMethod().getAnnotations();
                Annotation[] filteredAnnotations = filterNonRequirePermissionAnnotations(methodAnnotations);
                hasPermission = permissionValidator.canDoSth(request, filteredAnnotations);
            }
            if (!hasPermission) {
                ResponseProcessor.sendJson(response, ResponseUtils.error("403", "无权限调用此接口"));
            }
            return hasPermission;
        }
        //其他错误情况统统不放行
        ResponseProcessor.sendJson(response, ResponseUtils.error("未知错误,handler不匹配"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private Annotation[] filterNonRequirePermissionAnnotations(Annotation[] annotations) {
        List<Annotation> result = new ArrayList<>();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getAnnotation(RequirePermission.class) != null) {
                result.add(annotation);
            }
        }
        return result.toArray(new Annotation[result.size()]);
    }
}
