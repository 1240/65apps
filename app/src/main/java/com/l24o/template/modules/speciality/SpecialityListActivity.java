package com.l24o.template.modules.speciality;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.l24o.template.Constants;
import com.l24o.template.R;
import com.l24o.template.common.mvp.BaseListActivity;
import com.l24o.template.data.realm.models.RealmSpecialty;
import com.l24o.template.di.AppComponent;
import com.l24o.template.modules.person.PersonListActivity;

import javax.inject.Inject;

/**
 * @author Alexander Popov on 18/07/2017.
 */
public class SpecialityListActivity extends BaseListActivity<RealmSpecialty>
        implements SpecialityListContract.ISpecialityListView {

    @Inject
    SpecialityListContract.ISpecialityListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onViewAttached();
    }

    @Override
    public void resolveDependencies(AppComponent appComponent) {
        DaggerSpecialityComponent.builder()
                .appComponent(appComponent)
                .specialityModule(new SpecialityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void beforeDestroy() {
        presenter.dropView();
    }

    @Override
    protected String getEmptyText() {
        return getString(R.string.persons_empty);
    }

    @Override
    protected boolean isSwipeRefreshLayoutEnable() {
        return true;
    }

    @Override
    protected void onSwipeToRefresh() {
        presenter.onSwipeToRefresh();
    }

    @NonNull
    @Override
    protected RecyclerView.Adapter<?> createAdapter() {
        return new SpecialityAdapter(dataset, person -> presenter.onItemClick(person));
    }

    @Override
    public void navigateToPersons(RealmSpecialty specialty) {
        Intent intent = new Intent(this, PersonListActivity.class)
                .putExtra(Constants.SPECIALITY_ID, specialty.getSpecialityId());
        startActivity(intent);
    }
}
