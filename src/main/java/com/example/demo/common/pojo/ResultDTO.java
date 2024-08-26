package com.example.demo.common.pojo;

import com.example.demo.common.enums.ResponseCodeEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ResultDTO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(ResponseCodeEnum.OK.getCode());
        resultDTO.setMessage(ResponseCodeEnum.OK.getMessage());
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> success(ResponseCodeEnum responseCode) {
        return error(responseCode);
    }

    public static <T> ResultDTO<T> error(ResponseCodeEnum responseCode) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(responseCode.getCode());
        resultDTO.setMessage(responseCode.getMessage());
        return resultDTO;
    }
}
