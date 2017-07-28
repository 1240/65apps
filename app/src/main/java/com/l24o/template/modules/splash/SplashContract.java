package com.l24o.template.modules.splash;

import com.l24o.template.common.mvp.MvpContract;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public interface SplashContract {

    interface ISplashView extends MvpContract.IView {
        void navigateToMain();
        void showTryAgainSnackbar();
    }

    interface ISplashPresenter extends MvpContract.IPresenter<ISplashView> {
        void onTryAgainClicked();
    }

}
