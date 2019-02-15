package io.mcsoft.aop.framework.util;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 注解工具类
 * Created by MC on 2019/2/12.
 *
 * @author MC
 * @date 2019/2/12
 */
public class AnnotationUtil {

    /**
     * 测试方法、类等位置上是否包含指定注解
     *
     * @param handler         Spring方法对象
     * @param annotationClass 指定注解
     * @return 是否包含注解
     */
    public static boolean testAnnotation(HandlerMethod handler, Class<? extends Annotation> annotationClass) {
        boolean result = false;
        Target target = annotationClass.getAnnotation(Target.class);
        if (null == target) {
            return handler.hasMethodAnnotation(annotationClass);
        }

        ElementType[] types = annotationClass.getAnnotation(Target.class).value();
        for (ElementType type : types) {
            if (type == ElementType.TYPE) {
                result |= AnnotatedElementUtils.hasAnnotation(
                        handler.getMethod().getDeclaringClass(), annotationClass);
            }
            if (type == ElementType.METHOD) {
                result |= handler.hasMethodAnnotation(annotationClass);
            }
            if (type == ElementType.ANNOTATION_TYPE) {
                result |= AnnotatedElementUtils.hasAnnotation(
                        handler.getMethod().getDeclaringClass(), annotationClass);
                result |= handler.hasMethodAnnotation(annotationClass);
            }
        }

        return result;
    }

}
