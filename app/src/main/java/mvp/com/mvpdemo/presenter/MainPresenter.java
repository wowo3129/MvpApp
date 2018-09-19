package mvp.com.mvpdemo.presenter;

import mvp.com.mvpdemo.view.IMainView;
import mvp.com.mvpdemo.base.BasePresenter;
import mvp.com.mvpdemo.model.IModel;
import mvp.com.mvpdemo.model.MainModelImpl;

/**
 * @author ydong
 */
public class MainPresenter extends BasePresenter<IMainView> {
    private IModel mainModel = new MainModelImpl();

    public String getNavigationData() {
        IMainView view = getView();
        if (view == null) {
            return "";
        }
        return mainModel.getNavigationData(view.getData());
    }
}
