package com.l24o.template.modules.splash;

import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@Module(includes = {SplashModule.Declarations.class})
class SplashModule {
    private final SplashContract.ISplashView view;

    public SplashModule(SplashContract.ISplashView view) {
        this.view = view;
    }

    @Provides
    SplashContract.ISplashView provideView() {
        return view;
    }

    @Module
    interface Declarations {

        @Binds
        @ActivityScrope
        SplashContract.ISplashPresenter bindPresenter(SplashPresenter presenter);

    }
}