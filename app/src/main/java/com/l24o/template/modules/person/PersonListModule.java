package com.l24o.template.modules.person;

import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@Module(includes = {PersonListModule.Declarations.class})
class PersonListModule {
    private final PersonListContract.IPersonListView view;

    public PersonListModule(PersonListContract.IPersonListView view) {
        this.view = view;
    }

    @Provides
    PersonListContract.IPersonListView provideView() {
        return view;
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScrope
        PersonListContract.IPersonListPresenter bindPresenter(PersonListPresenter mainPresenter);

    }
}