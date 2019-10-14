package com.example.sphproject.Models;

import okhttp3.ResponseBody;

public class BaseResponse {
    public DataResponse serviceResponse = null;
    public String errorMsg = null;
    public Boolean isSuccess = true;

    public BaseResponse(DataResponse serviceResponse, String errorMsg,  Boolean isSuccess) {
        this.serviceResponse = serviceResponse;
        this.errorMsg = errorMsg;
        this.isSuccess = isSuccess;
    }
}
