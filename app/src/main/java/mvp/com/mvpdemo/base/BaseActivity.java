package mvp.com.mvpdemo.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author ydong
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    /**
     * 将presenter写到基类里面,实现绑定和解绑定View,V 和 P 可以是任何类型的，扩展性比较好
     */
    public P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*子类继承父类，实现父类的方法*/
        presenter = createPresenter();
        /*因为是抽象类，所以这里的this可以是V类型的任何一个动态传进来的View*/
        presenter.attachView((V) this);
    }

    /**
     * 创建P的实例，放在Activity子类中实现
     *
     * @return {@link BasePresenter} 的类型的任一个实现类
     */
    public abstract P createPresenter();

    /**
     * 在Activity销毁时解绑定View和业务逻辑，
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
