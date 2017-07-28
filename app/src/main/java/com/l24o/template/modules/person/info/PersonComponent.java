package com.l24o.template.modules.person.info;

import com.l24o.template.di.AppComponent;
import com.l24o.template.di.scopes.ActivityScrope;

import dagger.Component;

/**
 * @author Alexander Popov on 28/07/2017.
 */

@ActivityScrope
@Component(modules = {PersonModule.class}, dependencies = {AppComponent.class})
public interface PersonComponent {
    void inject(PersonActivity personActivity);
}
