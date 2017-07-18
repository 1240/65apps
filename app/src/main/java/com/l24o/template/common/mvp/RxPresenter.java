package com.l24o.template.common.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public abstract class RxPresenter<V extends MvpContract.IView> extends BasePresenter<V> {

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    public RxPresenter(V view) {
        super(view);
    }

    @Override
    public void onViewDetached() {
        subscriptions.clear();
        super.onViewDetached();
    }

    protected void registerSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    protected void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
    }
}
