package com.l24o.template.modules.main;

import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@Module(includes = {MainModule.Declarations.class})
class MainModule {
    private final MainContract.IMainView view;

    public MainModule(MainContract.IMainView view) {
        this.view = view;
    }

    @Provides
    MainContract.IMainView provideView() {
        return view;
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScrope
        MainContract.IMainPresenter bindPresenter(MainPresenter mainPresenter);

    }
}