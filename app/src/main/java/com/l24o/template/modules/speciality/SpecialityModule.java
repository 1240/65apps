package com.l24o.template.modules.speciality;

import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@Module(includes = {SpecialityModule.Declarations.class})
class SpecialityModule {
    private final SpecialityListContract.ISpecialityListView view;

    public SpecialityModule(SpecialityListContract.ISpecialityListView view) {
        this.view = view;
    }

    @Provides
    SpecialityListContract.ISpecialityListView provideView() {
        return view;
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScrope
        SpecialityListContract.ISpecialityListPresenter bindPresenter(SpecialityListPresenter mainPresenter);

    }
}