package com.flavors.mvp.presenter;


import com.flavors.mvp.base.BasePresenter;
import com.flavors.mvp.model.IModel;
import com.flavors.mvp.model.MainModelImpl;
import com.flavors.mvp.view.IMainView;

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
