package com.example.sphproj.Repository

import androidx.lifecycle.MutableLiveData
import com.example.sphproject.DataApi
import com.example.sphproject.Models.BaseResponse
import com.example.sphproject.Models.DataResponse
import com.example.sphproject.Models.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MobileDataRepository {

    private val dataApi: DataApi

    init {
        dataApi = NetworkHelper().apiService
    }

    fun getMobileVolData(source: String, key: String): MutableLiveData<BaseResponse> {
        val newsData = MutableLiveData<BaseResponse>()
        dataApi.getNewsList(source, key).enqueue(object : Callback<DataResponse> {
            override fun onResponse(
                call: Call<DataResponse>,
                response: Response<DataResponse>
            ) {
                if (response.isSuccessful()) {

                    newsData.setValue(
                        BaseResponse(
                            response.body(), response.errorBody()
                        )
                    )
                } else {
                    newsData.setValue( BaseResponse(
                        response.body(), response.errorBody()
                    ))
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                newsData.setValue(null)
            }
        })
        return newsData
    }

    companion object {

        private var mobileDataRepository: MobileDataRepository? = null

        val instance: MobileDataRepository
            get() {
                if (mobileDataRepository == null) {
                    mobileDataRepository = MobileDataRepository()
                }
                return mobileDataRepository as MobileDataRepository
            }
    }
}