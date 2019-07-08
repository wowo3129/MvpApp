package com.flavors.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.flavors.mvp.base.BaseActivity;
import com.flavors.mvp.presenter.MainPresenter;
import com.flavors.mvp.view.IMainView;
import com.plat.sdk.R;

/**
 * @author ydong
 */
public class MainActivity extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        /*直接调用父类里的presenter实例*/
        String navigationData = presenter.getNavigationData();
        Toast.makeText(this, navigationData, Toast.LENGTH_SHORT).show();
    }

    /**
     * 在抽象类{@link BaseActivity#createPresenter()}里写一个未实现的方法，
     * 这里通过实例化返回实例对象，在抽象类里的{@link BaseActivity#onCreate(Bundle)}
     *
     * @return MainPresenter 实例
     */
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public String getData() {
        return "初始化数据";
    }

    //================START1

    /**
     * 肥兔子:
     * 泛型中＜K extends Object＞,extends并不代表继承，它是类型范围限制
     * <p>
     * 肥兔子:
     *
     * @王争 泛型中的extends 跟普通类的extends有区别，泛型标记一个范围，你的Rxpreasenrter 实现了 BasePresenter1，那他就在这个限定范围内
     */
    class WelcomeActivity extends BaseActivity1<RxPresenter> {


    }

    interface BasePresenter1<T> {

    }

    abstract class BaseActivity1<T extends BasePresenter1> {

    }


    class RxPresenter implements BasePresenter1 {

    }

    //================START2
    class WelcomeActivity1 extends BaseActivity1<RxPresenter> {


    }

    abstract class BasePresenter1<T> {

    }

    abstract class BaseActivity1<T extends BasePresenter1> {

    }


    class RxPresenter1 extends BasePresenter1 {
    }


}
