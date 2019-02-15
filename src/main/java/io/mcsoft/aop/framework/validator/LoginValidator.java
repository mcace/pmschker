package io.mcsoft.aop.framework.validator;

import io.mcsoft.aop.framework.copied.AccountPO;
import io.mcsoft.aop.framework.copied.IndexService;
import io.mcsoft.aop.framework.copied.Response;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 校验是否登录的工具类
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
@Component
public class LoginValidator extends AbstractHaveLastResponseValidator {
    @Resource
    private IndexService indexService;

    public static ThreadLocal<AccountPO> CURRENT_ACCOUNT = new ThreadLocal<>();

    @Override
    public Response<AccountPO> getValidateResponse(HttpServletRequest request, Object... params) {
        Response<AccountPO> response = indexService.getLoginUserInfo(request);
        //校验登陆时，将AccountPO存入，方便后续使用
        CURRENT_ACCOUNT.remove();
        CURRENT_ACCOUNT.set(response.getData());
        return response;
    }
}