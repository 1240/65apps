package com.l24o.template.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.l24o.template.TemplateApplication;
import com.l24o.template.di.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public abstract class MvpActivity extends AppCompatActivity implements MvpContract.IView {

    private Unbinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resolveDependencies(application().getAppComponent());
        int layoutRes = getActivityLayoutRes();
        if (layoutRes != -1) {
            setContentView(layoutRes);
        }
        binder = ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        beforeDestroy();
        super.onDestroy();
        binder.unbind();
    }

    protected void initViews() {}

    //if layout not need -> return -1
    protected abstract int getActivityLayoutRes();

    public abstract void resolveDependencies(AppComponent appComponent);

    public abstract void beforeDestroy();

    @Override
    public TemplateApplication application() {
        return (TemplateApplication) TemplateApplication.getInstance();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(application(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(application(), messageResId, Toast.LENGTH_SHORT).show();
    }
}
