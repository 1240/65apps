package com.l24o.template.modules.person;

import com.l24o.template.di.AppComponent;
import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Component;

/**
 * @author Alexander Popov on 18/07/2017.
 */

@ActivityScrope
@Component(modules = {PersonListModule.class}, dependencies = {AppComponent.class})
interface PersonListComponent {
    void inject(PersonListActivity activity);
}
