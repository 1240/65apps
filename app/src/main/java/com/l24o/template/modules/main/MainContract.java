package com.l24o.template.modules.main;

import com.l24o.template.common.mvp.MvpContract;

/**
 * @author Alexander Popov on 18/07/2017.
 */

public interface MainContract {

    interface IMainView extends MvpContract.IView {

    }

    interface IMainPresenter extends MvpContract.IPresenter<IMainView> {

    }

}
