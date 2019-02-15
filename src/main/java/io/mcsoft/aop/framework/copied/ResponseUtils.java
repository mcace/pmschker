package io.mcsoft.aop.framework.copied;

public class ResponseUtils {
    public static boolean isSuccess(Response response) {
        return "0".equals(response.getStatus());
    }

    public static Response error(String msg) {
        return error("-1", msg, null);
    }

    public static Response error(String code, String msg) {
        return error(code, msg, null);
    }

    public static <T> Response<T> error(String code, String msg, T obj) {
        return new Response(code, msg, obj);
    }
}