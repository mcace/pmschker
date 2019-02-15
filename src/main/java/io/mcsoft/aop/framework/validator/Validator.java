package io.mcsoft.aop.framework.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验器，做具体校验工作
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
public interface Validator {
    /**
     * 校验请求是否通过
     *
     * @param request 请求实体
     * @param params  一些其他参数用于校验
     * @return 是否通过
     */
    boolean canDoSth(HttpServletRequest request, Object... params);
}
