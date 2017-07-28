package com.l24o.template.modules.splash;

import com.l24o.template.common.mvp.RxPresenter;
import com.l24o.template.data.rest.repositories.MainRepository;
import com.l24o.template.data.rest.repositories.RealmRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class SplashPresenter extends RxPresenter<SplashContract.ISplashView> implements SplashContract.ISplashPresenter {

    @Inject
    RealmRepository realmRepository;
    @Inject
    MainRepository mainRepository;

    @Inject
    public SplashPresenter(SplashContract.ISplashView view) {
        super(view);
    }

    @Override
    public void onTryAgainClicked() {
        checkData();
    }

    @Override
    public void onViewAttached() {
        super.onViewAttached();
        checkData();
    }

    @Override
    public void onViewDetached() {
        realmRepository.close();
        super.onViewDetached();
    }

    private void checkData() {
        subscriptions.add(realmRepository.isCached()
                .flatMap(isCached -> {
                    if (isCached) {
                        return Observable.just(null);
                    } else {
                        return mainRepository.getInfo();
                    }
                })
                .flatMap(apiResponse -> {
                    if (apiResponse != null) {
                        return realmRepository.saveApiResponse(apiResponse);
                    } else {
                        return Observable.just(false);
                    }
                })
                .subscribe(isCached -> view.navigateToMain()
                        , error -> {
                            view.showTryAgainSnackbar();
                            error.printStackTrace();
                        }));
    }
}
