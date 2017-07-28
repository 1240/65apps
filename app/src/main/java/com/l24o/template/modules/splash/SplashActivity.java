package com.l24o.template.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import com.l24o.template.R;
import com.l24o.template.common.mvp.MvpActivity;
import com.l24o.template.di.AppComponent;
import com.l24o.template.modules.speciality.SpecialityListActivity;

import javax.inject.Inject;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class SplashActivity extends MvpActivity implements SplashContract.ISplashView {

    @Inject
    SplashContract.ISplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onViewAttached();
    }

    @Override
    protected int getActivityLayoutRes() {
        return -1;
    }

    @Override
    public void navigateToMain() {
        finish();
        startActivity(new Intent(this, SpecialityListActivity.class));
    }

    @Override
    public void showTryAgainSnackbar() {
        Snackbar.make(findViewById(android.R.id.content), R.string.somthing_wrong, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, v -> presenter.onTryAgainClicked())
                .show();
    }


    @Override
    public void resolveDependencies(AppComponent appComponent) {
        DaggerSplashComponent.builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void beforeDestroy() {
        presenter.dropView();
    }
}
