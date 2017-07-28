package com.l24o.template.modules.speciality;

import com.l24o.template.common.mvp.RxPresenter;
import com.l24o.template.data.realm.models.RealmSpecialty;
import com.l24o.template.data.rest.repositories.MainRepository;
import com.l24o.template.data.rest.repositories.RealmRepository;

import java.util.Collections;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author Alexander Popov on 18/07/2017.
 */

public class SpecialityListPresenter extends RxPresenter<SpecialityListContract.ISpecialityListView> implements SpecialityListContract.ISpecialityListPresenter {

    @Inject
    RealmRepository realmRepository;

    @Inject
    MainRepository mainRepository;

    @Inject
    public SpecialityListPresenter(SpecialityListContract.ISpecialityListView view) {
        super(view);
    }

    @Override
    public void onViewAttached() {
        super.onViewAttached();
        subscriptions.add(fetchSpec());
    }

    @Override
    public void onItemClick(RealmSpecialty item) {
        view.navigateToPersons(item);
    }

    @Override
    public void onSwipeToRefresh() {
        subscriptions.add(swipeToRefresh());
    }

    @Override
    public void onViewDetached() {
        realmRepository.close();
        super.onViewDetached();
    }

    private Subscription swipeToRefresh() {
        return mainRepository.getInfo()
                .flatMap(result -> realmRepository.saveApiResponse(result))
                .flatMap(result -> realmRepository.fetchSpec())
                .subscribe(data -> view.showData(data),
                        error -> {
                            view.showData(Collections.emptyList());
                            view.showMessage(error.getMessage());
                        });
    }

    private Subscription fetchSpec() {
        return realmRepository.fetchSpec()
                .subscribe(data -> view.showData(data),
                        error -> {
                            view.showData(Collections.emptyList());
                            view.showMessage(error.getMessage());
                        });
    }


}
