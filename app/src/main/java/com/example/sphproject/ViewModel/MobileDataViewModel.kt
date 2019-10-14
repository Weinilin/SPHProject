package com.example.sphproj.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sphproj.Repository.MobileDataRepository
import com.example.sphproject.Models.BaseResponse


class MobileDataViewModel(application: Application) : AndroidViewModel(application) {


    private var mutableLiveData: MutableLiveData<BaseResponse>? = null
    private var mobileDataRepository: MobileDataRepository? = null

    fun init() {
        if (mutableLiveData != null) {
            return
        }
        mobileDataRepository = MobileDataRepository()
    }

    fun getMobileVolData(): MutableLiveData<BaseResponse>? {
        return mobileDataRepository?.getMobileVolData("a807b7ab-6cad-4aa6-87d0-e283a7353a0f", "59")

    }
}