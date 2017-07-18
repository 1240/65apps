package com.l24o.template.modules.main;

import com.l24o.template.di.AppComponent;
import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Component;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@ActivityScrope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
interface MainComponent {
    void inject(MainActivity mainActivity);
}
