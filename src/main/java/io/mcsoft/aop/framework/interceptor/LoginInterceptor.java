package io.mcsoft.aop.framework.interceptor;

import io.mcsoft.aop.framework.annotation.login.RequireLogin;
import io.mcsoft.aop.framework.copied.AccountPO;
import io.mcsoft.aop.framework.copied.ResponseUtils;
import io.mcsoft.aop.framework.util.AnnotationUtil;
import io.mcsoft.aop.framework.util.ResponseProcessor;
import io.mcsoft.aop.framework.validator.LoginValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，在调用Controller前验证用户是否登录
 * Created by MC on 2019/1/24.
 *
 * @author MC
 * @date 2019/1/24
 */
@Component("loginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private LoginValidator loginValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            boolean logged = true;
            if (AnnotationUtil.testAnnotation((HandlerMethod) handler, RequireLogin.class)) {
                logged = loginValidator.canDoSth(request);
                if (!logged) {
                    ResponseProcessor.sendJson(response, loginValidator.getLastResponse());
                } else {
                    AccountPO account = LoginValidator.CURRENT_ACCOUNT.get();
                    // TODO: 2019/2/11 注入account到Controller
                    //((HandlerMethod) handler).getMethodParameters()[0]
                }
            }
            return logged;
        }
        //其他错误情况统统不放行
        ResponseProcessor.sendJson(response, ResponseUtils.error("未知错误，handler不匹配"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
