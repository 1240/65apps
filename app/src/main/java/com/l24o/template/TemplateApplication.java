package com.l24o.template;

import android.app.Application;

import com.l24o.template.di.AppComponent;
import com.l24o.template.di.DaggerAppComponent;
import com.l24o.template.di.modules.AppModule;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public class TemplateApplication extends Application {

    private AppComponent appComponent;
    private static TemplateApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        ButterKnife.setDebug(true);
        Realm.init(this);
    }

    public static TemplateApplication getInstance() {
        return application;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
