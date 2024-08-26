package com.example.demo.common.exception.handler;

import com.example.demo.common.enums.ResponseCodeEnum;
import com.example.demo.common.exception.BaseException;
import com.example.demo.common.pojo.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    /**
     * 处理BaseException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResultDTO<BaseException> handlerGlobalException(BaseException e) {
        log.error(e.getMessage(), e);
        return ResultDTO.error(e.getResponseCode());
    }

    /**
     * 处理BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO<BindException> handlerBindException(BindException e) {
        log.error(e.getMessage(), e);
        return ResultDTO.error(ResponseCodeEnum.BAD_REQUEST);
    }

    /**
     * 处理Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultDTO<Exception> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultDTO.error(ResponseCodeEnum.ERROR);
    }
}
