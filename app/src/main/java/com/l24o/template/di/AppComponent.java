package com.l24o.template.di;

import com.google.gson.Gson;
import com.l24o.template.data.rest.AuthProvider;
import com.l24o.template.data.rest.datasource.MainDataSource;
import com.l24o.template.data.rest.repositories.RealmRepository;
import com.l24o.template.di.modules.AppModule;
import com.l24o.template.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import okhttp3.OkHttpClient;

/**
 * @author Alexander Popov on 11/07/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Gson provideGson();
    Realm provideRealm();
    OkHttpClient provideHttpClient();
    AuthProvider provideAuthProvider();
    MainDataSource provideMainDataSource();
    RealmRepository provideRealmRepository();
}
