package com.l24o.template.common.mvp;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public abstract class BasePresenter<V extends MvpContract.IView> implements MvpContract.IPresenter<V> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void takeView(V view) {
        this.view = view;
    }

    @Override
    public void onViewAttached() {

    }

    @Override
    public void dropView() {
        view = null;
        onViewDetached();
    }

    @Override
    public void onViewDetached() {

    }
}
