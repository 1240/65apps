package com.l24o.template.modules.person;

import com.l24o.template.common.mvp.RxPresenter;
import com.l24o.template.data.realm.models.RealmPerson;
import com.l24o.template.data.rest.repositories.MainRepository;
import com.l24o.template.data.rest.repositories.RealmRepository;

import java.util.Collections;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author Alexander Popov on 18/07/2017.
 */

public class PersonListPresenter extends RxPresenter<PersonListContract.IPersonListView>
        implements PersonListContract.IPersonListPresenter {

    @Inject
    RealmRepository realmRepository;

    @Inject
    public PersonListPresenter(PersonListContract.IPersonListView view) {
        super(view);
    }

    @Override
    public void init(int uuid) {
        subscriptions.add(
                realmRepository
                        .fetchPersonBySpecialityId(uuid)
                        .subscribe(realmPeople -> view.showData(realmPeople),
                                error -> {
                                    view.showData(Collections.emptyList());
                                    view.showMessage(error.getMessage());
                                })
        );
    }

    @Override
    public void onSwipeToRefresh() {
    }

    @Override
    public void onViewDetached() {
        realmRepository.close();
        super.onViewDetached();
    }

    @Override
    public void onItemClick(RealmPerson person) {
        view.navigateToPerson(person);
    }
}
