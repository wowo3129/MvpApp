package com.flavors.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author ydong
 */
public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> result = new MutableLiveData<>();

    public MutableLiveData<Integer> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<String> getResultLiveData() {
        return result;
    }

    public MyViewModel() {
        super();
        initData();
    }

    public void initData() {
        mutableLiveData.setValue(9);
    }




}
