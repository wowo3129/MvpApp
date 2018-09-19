package mvp.com.mvpdemo;

import android.os.Bundle;
import android.widget.Toast;

import mvp.com.mvpdemo.base.BaseActivity;
import mvp.com.mvpdemo.presenter.MainPresenter;
import mvp.com.mvpdemo.view.IMainView;

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
        return "初始化地图";
    }

}
