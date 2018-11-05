package thread.base;


import java.lang.ref.WeakReference;

/**
 * @author ydong
 */
public abstract class BasePresenter<V> {

    private WeakReference<V> vWeakReference;

    /**
     * 绑定View
     * Presenter 如果持有 Activity 的强引用，在请求结束之前 Activity 被销毁了，
     * 那么由于网络请求还没有返回，导致 Presenter 一直持有 Activity 对象，使得
     * Activity 无法被回收，此时就容易发生内存泄漏，解决这个问题需要通过弱引用来解决
     *
     * @param view 主UI
     */
    void attachView(V view) {
        vWeakReference = new WeakReference<>(view);
    }

    /**
     * 解除View的绑定
     */
    void detachView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
        }
    }

    /**
     * @return 返回当前的View实例
     */
    protected V getView() {
        return vWeakReference.get();
    }
}
