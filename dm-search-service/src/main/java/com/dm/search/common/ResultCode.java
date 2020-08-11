package com.dm.search.common;

/**
 * 常用API操作码
 *
 * @author dm
 * @date 2020/04/09
 * @since JDK1.8
 */
public enum ResultCode implements IErrorCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),

    /**
     * 参数检验失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),

    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    /**
     * 请求头中的token为空
     */
    AUTHORIZATION_HEADER_IS_EMPTY(600,"请求头中的token为空"),

    /**
     * 远程获取TokenKey异常
     */
    GET_TOKEN_KEY_ERROR(601,"远程获取TokenKey异常"),

    /**
     * 生成公钥异常
     */
    GEN_PUBLIC_KEY_ERROR(602,"生成公钥异常"),

    /**
     * token校验异常
     */
    JWT_TOKEN_EXPIRE(603,"token校验异常"),

    /**
     * 后端服务触发流控
     */
    TOMANY_REQUEST_ERROR(429,"后端服务触发流控"),

    /**
     * 后端服务触发降级
     */
    BACKGROUD_DEGRADE_ERROR(604,"后端服务触发降级"),

    /**
     * 网关服务异常
     */
    BAD_GATEWAY(502,"网关服务异常"),

    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
