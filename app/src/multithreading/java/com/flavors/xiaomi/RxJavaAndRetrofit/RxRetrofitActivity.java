package com.flavors.xiaomi.RxJavaAndRetrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.plat.sdk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ydong
 */
public class RxRetrofitActivity extends AppCompatActivity {

    private Button gankAPiGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit);
        initView();
    }

    private void initView() {
        gankAPiGet = findViewById(R.id.gankAPiGet);
        gankAPiGet.setOnClickListener(v -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://gank.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            GankApi gankApi = retrofit.create(GankApi.class);
            Call<GankBean> android = gankApi.getData("Android", "10", "1");
            android.enqueue(new Callback<GankBean>() {
                @Override
                public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                    Log.d("ydong", "onResponse: " + response.body().getResults().get(0).getDesc());
                }

                @Override
                public void onFailure(Call<GankBean> call, Throwable t) {
                    Log.e("ydong", "onFailure: " + t);

                }
            });
        });


    }
}
