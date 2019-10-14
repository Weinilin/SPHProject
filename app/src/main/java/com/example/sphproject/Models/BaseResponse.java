package com.example.sphproject.Models;

import okhttp3.ResponseBody;

public class BaseResponse {
    public DataResponse serviceResponse;
    public ResponseBody errorResponse;

    public BaseResponse(DataResponse serviceResponse, ResponseBody errorResponse) {
        this.serviceResponse = serviceResponse;
        this.errorResponse = errorResponse;
    }
}
