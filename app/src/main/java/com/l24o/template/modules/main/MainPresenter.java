package com.l24o.template.modules.main;

import com.l24o.template.common.mvp.RxPresenter;

import javax.inject.Inject;

/**
 * @author Alexander Popov on 18/07/2017.
 */

public class MainPresenter extends RxPresenter<MainContract.IMainView> implements MainContract.IMainPresenter {

    @Inject
    public MainPresenter(MainContract.IMainView view) {
        super(view);
    }


}
