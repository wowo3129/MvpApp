package com.flavors.xiaomi.RxJavaAndRetrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.plat.sdk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ydong
 */
public class RxRetrofitActivity extends AppCompatActivity {
    @BindView(R.id.gankAPiGet) Button gankAPiGet;
    @BindView(R.id.gankAPiGetWithUrl) Button gankAPiGetWithUrl;
    @BindView(R.id.gankAPiPOST) Button gankAPiPOST;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit);
        bind = ButterKnife.bind(this);

    }

    @OnClick({R.id.gankAPiGet, R.id.gankAPiPOST, R.id.gankAPiGetWithUrl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gankAPiPOST:
                gankPOSTRequest();
                break;
            case R.id.gankAPiGet:
                gankGetRequest();
                break;
            case R.id.gankAPiGetWithUrl:
                gankGetWithUrlRequest();
                break;
            default:
                break;

        }
    }

    private void gankGetRequest() {
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
    }

    private void gankGetWithUrlRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankApi gankApi = retrofit.create(GankApi.class);
        Call<GankBean> android = gankApi.getDataWithUrl("http://gank.io/api/data/Android/10/1");
        android.enqueue(new Callback<GankBean>() {
            @Override
            public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                Log.d("ydong", "gankGetWithUrlRequest onResponse: " + response.body().getResults().get(0).getDesc());
            }

            @Override
            public void onFailure(Call<GankBean> call, Throwable t) {
                Log.e("ydong", "gankGetWithUrlRequest onFailure: " + t);

            }
        });
    }


    private void gankPOSTRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankApi gankApi = retrofit.create(GankApi.class);
        Call<ResponseBody> responseBodyCall = gankApi.postData("http://square.github.io/retrofit/",
                "测试数据",
                "未来安卓大佬",
                "Android",
                "true");
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("ydong", "gankPOSTRequest onResponse: " + response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ydong", "gankPOSTRequest onResponse: " + t);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
