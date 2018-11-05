package thread.presenter;


import com.plat.sdk.model.IModel;

import thread.base.BasePresenter;
import thread.model.MainModelImpl;
import thread.view.IMainView;

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
