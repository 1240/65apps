package com.l24o.template.modules.person.info;

import com.l24o.template.common.mvp.RxPresenter;
import com.l24o.template.data.rest.repositories.RealmRepository;

import javax.inject.Inject;

/**
 * @author Alexander Popov on 28/07/2017.
 */

public class PersonPresenter extends RxPresenter<PersonContract.IPersonView>
        implements PersonContract.IPersonPresenter {

    @Inject
    RealmRepository realmRepository;

    @Inject
    public PersonPresenter(PersonContract.IPersonView view) {
        super(view);
    }

    @Override
    public void init(String personId) {
        subscriptions.add(
                realmRepository.fetchPersonById(personId)
                        .subscribe(person -> {
                                    view.fillView(person);
                                    view.showData(person.getSpecialtyList());
                                },
                                error -> {
                                    view.showMessage(error.getMessage());
                                    error.printStackTrace();
                                })
        );
    }

    @Override
    public void onSwipeToRefresh() {

    }
}
