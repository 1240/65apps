package com.l24o.template.common.mvp;

import com.l24o.template.TemplateApplication;

import java.util.List;

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

    interface IListView<M> extends IView {
        void setLoadingVisible(boolean isVisible);
        void showData(List<M> dataList);
    }

    interface IListPresenter<M, V extends IListView<M>> extends IPresenter<V> {
        void onSwipeToRefresh();
    }

}
