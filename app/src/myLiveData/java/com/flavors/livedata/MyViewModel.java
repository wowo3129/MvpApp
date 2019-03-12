package com.flavors.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author ydong
 */
public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getMutableLiveData() {
        return mutableLiveData;
    }

    public MyViewModel() {
        super();
        initData();
    }

    public void initData() {
        mutableLiveData.setValue(9);
    }

}
