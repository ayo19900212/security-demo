package com.example.demo.common.enums;

/**
 * 响应状态码
 */
public enum ResponseCodeEnum {

    OK(200, "请求成功"),
    BAD_REQUEST(400, "失败的请求"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求找不到"),
    NOT_ACCEPTABLE(406, "不可访问"),
    CONFLICT(409, "冲突"),
    TOKEN_ABNORMAL(410,"token异常"),
    TOKEN_EXPIRED(411,"token已过期"),
    LOGIN_SUCCESS(412,"登录成功"),
    LOGOUT_SUCCESS(413,"退出成功"),
    USERNAME_OR_PASSWORD_ERROR(414,"用户名或密码错误"),
    LOGIN_FIRST(414,"请先登录"),
    ERROR(500, "服务器发生异常"),

    USERNAME_EXISTS(999, "账号已存在，请登录"),
    USERNAME_NOT_EXISTS(999, "账号不存在，请注册"),
    PASSWORD_ERROR(1000, "密码不正确");

    private final Integer code;

    private final String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
