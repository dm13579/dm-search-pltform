package com.dm.search.common;

/**
 * 封装API的错误码
 *
 * @author dm
 * @date 2020/04/09
 * @since JDK1.8
 */
public interface IErrorCode {

    long getCode();

    String getMessage();
}
