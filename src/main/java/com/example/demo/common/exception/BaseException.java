package com.example.demo.common.exception;

import com.example.demo.common.enums.ResponseCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private ResponseCodeEnum responseCode;

    public BaseException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        setResponseCode(responseCode);
    }
}
