package com.l24o.template.modules.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.l24o.template.R;
import com.l24o.template.common.mvp.MvpActivity;
import com.l24o.template.di.AppComponent;

import javax.inject.Inject;

/**
 * @author Alexander Popov on 18/07/2017.
 */
public class MainActivity extends MvpActivity implements MainContract.IMainView {

    @Inject
    MainContract.IMainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter.onViewAttached();
    }

    @Override
    public void resolveDependencies(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void beforeDestroy() {
        presenter.dropView();
    }

    private void initViews() {

    }
}
