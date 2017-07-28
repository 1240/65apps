package com.l24o.template.di.modules;

import com.google.gson.Gson;
import com.l24o.template.Constants;
import com.l24o.template.data.rest.AuthProvider;
import com.l24o.template.data.rest.TemplateInterceptor;
import com.l24o.template.data.rest.datasource.MainDataSource;
import com.l24o.template.data.rest.repositories.RealmRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Alexander Popov on 11/07/2017.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideDefaultHTTPClient(TemplateInterceptor templateInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(templateInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public TemplateInterceptor provideTemplateInterceptor(AuthProvider authProvider) {
        return new TemplateInterceptor(authProvider);
    }

    @Provides
    @Singleton
    public AuthProvider provideAuthProvider() {
        return new AuthProvider();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitAdapter(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.API_MAIN_ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    public MainDataSource provideMainDataSource(Retrofit retrofit) {
        return retrofit.create(MainDataSource.class);
    }

    @Provides
    public RealmRepository provideRealmRepository(Realm realm) {
        return new RealmRepository(realm);
    }

}
