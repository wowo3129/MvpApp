package com.plat.sdk.presenter;

import com.plat.sdk.base.BasePresenter;
import com.plat.sdk.view.IMainView;
import com.plat.sdk.model.IModel;
import com.plat.sdk.model.MainModelImpl;


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
