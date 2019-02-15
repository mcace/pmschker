package io.mcsoft.aop.framework.validator.permission.handler;

/**
 * Created by MC on 2019/2/11.
 *
 * @author MC
 * @date 2019/2/11
 */
public abstract class AbstractAnnotationValidateHandler implements AnnotationValidateHandler{
    private Class relatedAnnotation;

    public AbstractAnnotationValidateHandler(Class relatedAnnotation) {
        this.relatedAnnotation = relatedAnnotation;
    }

    @Override
    public Class getRelatedAnnotation() {
        return this.relatedAnnotation;
    }
}
