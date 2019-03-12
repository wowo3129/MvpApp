package com.flavors.livedata;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.plat.sdk.R;

/**
 * @author ydong
 * @link {https://blog.csdn.net/Alexwll/article/details/83302173}
 * @link {https://developer.android.google.cn/jetpack/}
 */
public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getMutableLiveData().observe(this, integer -> {
            Log.d("ydong", "result::" + integer);
        });
    }


}
