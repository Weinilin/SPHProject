package com.example.sphproject;

import android.os.Parcel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sphproject.Models.BaseResponse;
import com.example.sphproject.Models.DataResponse;
import com.example.sphproject.Repository.MobileDataRepository;
import com.example.sphproject.ViewModel.MobileDataViewModel;

import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Mock
    MobileDataRepository repository;
    @Mock
    Observer<BaseResponse> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new MobileDataRepository();
    }

    @Test
    public void testApiFailureObserverValue() {
        // Mock API response
        MobileDataRepository mock = Mockito.mock(MobileDataRepository.class);
        MutableLiveData<BaseResponse> newsData = new MutableLiveData<>();
        BaseResponse baseResponse = new BaseResponse(null, "Network Error", false);

        newsData.setValue(
                baseResponse
        );

        Mockito.when(mock.getMobileVolData("", "")).thenReturn(newsData);
        mock.getMobileVolData("", "").observeForever(observer);
        Mockito.verify(observer).onChanged(baseResponse);
    }


    @Test
    public void testApiSuccessObserverValue() {
        // Mock API response
        MobileDataRepository mock = Mockito.mock(MobileDataRepository.class);
        MutableLiveData<BaseResponse> newsData = new MutableLiveData<>();
        BaseResponse baseResponse = new BaseResponse(null, "", true);

        newsData.setValue(
                baseResponse
        );

        Mockito.when(mock.getMobileVolData("", "")).thenReturn(newsData);
        mock.getMobileVolData("", "").observeForever(observer);
        Mockito.verify(observer).onChanged(baseResponse);
    }


    @Test
    public void testApiFetchDataFailureResponse() {
        // Mock API response
        MobileDataRepository mock = Mockito.mock(MobileDataRepository.class);
        MutableLiveData<BaseResponse> newsData = new MutableLiveData<>();
        BaseResponse baseResponse = new BaseResponse(null, "Network Error", false);

        newsData.setValue(
                baseResponse
        );
        IOException failure =  new IOException("Network Error");
        Call<DataResponse> networkError = Calls.failure(failure);
        Mockito.when(mock.callMobileDataAPI("","")).thenReturn(networkError);

        mock.callMobileDataAPI("","").enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Assert.assertEquals(t.getMessage(), "Network Error");
            }
        });
    }

    @Test
    public void testApiFetchDataSuccessResponse() {
        // Mock API response
        MobileDataRepository mock = Mockito.mock(MobileDataRepository.class);
        DataResponse dataResponse = new DataResponse("google.com", true, null);

        Call<DataResponse> successRep = Calls.response(dataResponse);
        Mockito.when(mock.callMobileDataAPI("","")).thenReturn(successRep);

        mock.callMobileDataAPI("","").enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                Assert.assertEquals(response.body().help, "google.com");
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
            }
        });
    }

}

