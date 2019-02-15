package io.mcsoft.aop.framework.copied;

import com.alibaba.fastjson.JSON;

public class Response<T> {

    //    响应代码
    private String status;

    //    响应信息
    private String msg;

    //    数据体
    private T data;

    public Response() {
    }

    public Response(String code, String msg, T data) {
        this.status = code;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData(Object object) {
        return data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return ResponseUtils.isSuccess(this);
    }
}
