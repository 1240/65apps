package com.l24o.template.modules.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.l24o.template.Constants;
import com.l24o.template.R;
import com.l24o.template.common.mvp.BaseListActivity;
import com.l24o.template.data.realm.models.RealmPerson;
import com.l24o.template.di.AppComponent;
import com.l24o.template.modules.person.info.PersonActivity;

import javax.inject.Inject;

/**
 * @author Alexander Popov on 18/07/2017.
 */
public class PersonListActivity extends BaseListActivity<RealmPerson>
        implements PersonListContract.IPersonListView {

    @Inject
    PersonListContract.IPersonListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.init(getIntent().getExtras().getInt(Constants.SPECIALITY_ID));
    }

    @Override
    public void resolveDependencies(AppComponent appComponent) {
        DaggerPersonListComponent.builder()
                .appComponent(appComponent)
                .personListModule(new PersonListModule(this))
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

    @NonNull
    @Override
    protected RecyclerView.Adapter<?> createAdapter() {
        return new PersonAdapter(dataset, person -> presenter.onItemClick(person));
    }

    @Override
    public void navigateToPerson(RealmPerson person) {
        Intent intent = new Intent(this, PersonActivity.class)
                .putExtra(Constants.PERSON_ID, person.getUuid());
        startActivity(intent);
    }
}
