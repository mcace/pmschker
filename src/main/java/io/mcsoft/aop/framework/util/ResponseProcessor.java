package io.mcsoft.aop.framework.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应处理工具
 * Created by MC on 2019/1/25.
 *
 * @author MC
 * @date 2019/1/25
 */
public class ResponseProcessor {

    /**
     * 通过HttpServletResponse对象的写流将json对象发送给客户端
     *
     * @param response HttpServletResponse对象
     * @param entity   需要转换的json对象
     */
    public static void sendJson(HttpServletResponse response, Object entity) {
        response.setContentType("application/json;charset=utf-8");
        String json = JSON.toJSONString(entity);
        try {
            response.getOutputStream().write(json.getBytes("utf8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
