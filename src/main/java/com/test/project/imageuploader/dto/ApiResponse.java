package com.test.project.imageuploader.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<U> {

    private String status;
    private String message;
    private int code;
    private U data;
    private U error;

    public static <U> ApiResponse<U> withData(U data) {
        ApiResponse<U> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        return apiResponse;
    }


    public static <U> ApiResponse<U> withError(String message) {
        ApiResponse<U> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(message);
        return apiResponse;
    }

    public static ApiResponse withNoData() {
        return new ApiResponse<>();
    }

    public ApiResponse<U> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResponse<U> setStatus(String status) {
        this.status = status;
        return this;
    }

    public ApiResponse<U> setCode(int code) {
        this.code = code;
        return this;
    }

    public ApiResponse<U> setData(U data) {
        this.data = data;
        return this;
    }
}
