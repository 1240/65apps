package com.l24o.template.modules.person.info;

import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@Module(includes = {PersonModule.Declarations.class})
class PersonModule {
    private final PersonContract.IPersonView view;

    public PersonModule(PersonContract.IPersonView view) {
        this.view = view;
    }

    @Provides
    PersonContract.IPersonView provideView() {
        return view;
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScrope
        PersonContract.IPersonPresenter bindPresenter(PersonPresenter presenter);

    }
}