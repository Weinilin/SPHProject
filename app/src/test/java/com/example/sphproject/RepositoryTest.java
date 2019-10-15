package com.example.sphproject;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sphproject.Models.BaseResponse;
import com.example.sphproject.Models.DataResponse;
import com.example.sphproject.Repository.MobileDataRepository;
import com.example.sphproject.ViewModel.MobileDataViewModel;

import org.bouncycastle.jcajce.provider.symmetric.ARC4;
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

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml", sdk = 24)
public class RepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Mock
    DataApi apiClient;
    private MobileDataViewModel viewModel;

    @Mock
    MainActivity mainActivity;
    @Mock
    MobileDataRepository repository;
    @Mock
    Observer<BaseResponse> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        viewModel = ViewModelProviders.of(mainActivity).get(MobileDataViewModel.class);
        repository = new MobileDataRepository();
    }

    @Test
    public void testApiFetchDataFailure() {
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
    public void testApiFetchDataSuccess() {
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

}

