package com.l24o.template.common.mvp;

import com.l24o.template.TemplateApplication;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public interface MvpContract {

    interface IView {
        TemplateApplication application();
        void showMessage(String message);
        void showMessage(int messageResId);
    }

    interface IPresenter<V extends IView> {
        void takeView(V view);
        void onViewAttached();
        void onViewDetached();
        void dropView();
    }


}
