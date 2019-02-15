package io.mcsoft.aop.framework.validator;

import io.mcsoft.aop.framework.copied.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * 可以获取上次请求返回对象的校验器
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
public abstract class AbstractHaveLastResponseValidator implements Validator {
    private ThreadLocal<Response> responseThreadLocal = new ThreadLocal<>();


    @Override
    public final boolean canDoSth(HttpServletRequest request, Object... params) {
        //改写canDoSth，使其成为模板方法，可以存储校验的Response，真正校验的功能通过调用validateAndGetResponse方法来实现
        removeLastResponse();
        Response response = getValidateResponse(request, params);
        setLastResponse(response);
        return (response != null && response.isSuccess());
    }

    /**
     * 获取响应以进行校验和存储
     */
    protected abstract Response getValidateResponse(HttpServletRequest request, Object... params);

    /**
     * 获取上次校验的返回值
     *
     * @return 上次校验的返回值，通过ThreadLocal实现
     */
    public Response getLastResponse() {
        return responseThreadLocal.get();
    }

    private void setLastResponse(Response response) {
        responseThreadLocal.set(response);
    }

    private void removeLastResponse() {
        responseThreadLocal.remove();
    }
}
