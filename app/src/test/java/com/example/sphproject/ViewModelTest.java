package com.example.sphproject;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.sphproject.Models.BaseResponse;
import com.example.sphproject.Models.DataResponse;
import com.example.sphproject.Repository.MobileDataRepository;
import com.example.sphproject.ViewModel.MobileDataViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

public class ViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    MobileDataViewModel mobileDataViewModel;
    @Mock
    Observer<BaseResponse> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mobileDataViewModel = new MobileDataViewModel();
    }

    @Test
    public void testApiViewModelSuccessValue() {
        // Mock API response
        MobileDataViewModel mock = Mockito.mock(MobileDataViewModel.class);
        MutableLiveData<BaseResponse> newsData = new MutableLiveData<>();
        BaseResponse baseResponse = new BaseResponse(new DataResponse("", true, null), "", true);

        newsData.setValue(
                baseResponse
        );
        Mockito.when(mock.getMobileVolData()).thenReturn(newsData);
        Objects.requireNonNull(mock.getMobileVolData()).observeForever(observer);

        Mockito.verify(observer).onChanged(baseResponse);
    }

    @Test
    public void testApiViewModelFailureValue() {
        // Mock API response
        MobileDataViewModel mock = Mockito.mock(MobileDataViewModel.class);
        MutableLiveData<BaseResponse> newsData = new MutableLiveData<>();
        BaseResponse baseResponse = new BaseResponse(null, "Error", false);

        newsData.setValue(
                baseResponse
        );
        Mockito.when(mock.getMobileVolData()).thenReturn(newsData);
        Objects.requireNonNull(mock.getMobileVolData()).observeForever(observer);

        Mockito.verify(observer).onChanged(baseResponse);
    }

    @Test
    public void testInitSuccessPopulateLiveData() {
        mobileDataViewModel.init("1");
        Assert.assertNotNull(mobileDataViewModel.getMobileVolData());
    }
}
