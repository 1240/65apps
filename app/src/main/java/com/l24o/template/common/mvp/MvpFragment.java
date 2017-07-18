package com.l24o.template.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.l24o.template.TemplateApplication;
import com.l24o.template.di.AppComponent;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public abstract class MvpFragment extends Fragment implements MvpContract.IView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(application().getAppComponent());
    }

    @Override
    public void onDestroy() {
        beforeDestroy();
        super.onDestroy();
    }

    abstract void injectDependencies(AppComponent appComponent);

    abstract void beforeDestroy();

    @Override
    public TemplateApplication application() {
        return (TemplateApplication) getActivity().getApplication();
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
