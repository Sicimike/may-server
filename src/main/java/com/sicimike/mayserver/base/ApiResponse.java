package com.sicimike.mayserver.base;

import com.alibaba.fastjson.JSON;
import com.sicimike.mayserver.enums.EnumResponseCode;
import lombok.Data;

/**
 * 通用返回对象
 * @author sicimike
 * @create 2018-12-13 14:11
 */
@Data
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T data;

    /**
     * 失败
     * @param code
     * @param message
     * @return
     */
    public static ApiResponse ofFail(Integer code, String message){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setData(null);
        return apiResponse;
    }

    /**
     * 失败
     * @param enumResponseCode
     * @return
     */
    public static ApiResponse ofFail(EnumResponseCode enumResponseCode){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(enumResponseCode.getCode());
        apiResponse.setMessage(enumResponseCode.getMessage());
        apiResponse.setData(null);
        return apiResponse;
    }

    /**
     * 成功，不需要返回数据
     * @param message
     * @return
     */
    public static ApiResponse ofMessage(String message){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(200);
        apiResponse.setMessage(message);
        apiResponse.setData(null);
        return apiResponse;
    }

    /**
     * 成功，需要返回数据
     * @param data
     * @return
     */
    public static ApiResponse ofSuccess(Object data){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(200);
        apiResponse.setMessage("success");
        apiResponse.setData(data);
        return apiResponse;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
