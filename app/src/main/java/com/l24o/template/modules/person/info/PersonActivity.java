package com.l24o.template.modules.person.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.l24o.template.Constants;
import com.l24o.template.R;
import com.l24o.template.common.mvp.BaseListActivity;
import com.l24o.template.common.utils.AgeUtils;
import com.l24o.template.data.realm.models.RealmPerson;
import com.l24o.template.data.realm.models.RealmSpecialty;
import com.l24o.template.di.AppComponent;
import com.l24o.template.modules.speciality.SpecialityAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Alexander Popov on 28/07/2017.
 */

public class PersonActivity extends BaseListActivity<RealmSpecialty>
        implements PersonContract.IPersonView {

    @Inject
    PersonContract.IPersonPresenter personPresenter;

    @BindView(R.id.tvFirstName)
    TextView tvFirstName;
    @BindView(R.id.tvLastName)
    TextView tvLastName;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personPresenter.init(getIntent().getExtras().getString(Constants.PERSON_ID));
    }

    @Override
    public void fillView(RealmPerson person) {
        tvFirstName.setText(getString(R.string.person_first_name, person.getFirstName()));
        tvLastName.setText(getString(R.string.person_last_name, person.getLastName()));
        if (person.getBirthday() != null) {
            tvBirthday.setText(getString(R.string.person_birthday, AgeUtils.formatDate(person.getBirthday())));
            int age = person.getAge();
            tvAge.setText(getString(AgeUtils.getAgeEnds(age), age));
        } else {
            tvBirthday.setText(getString(R.string.birthday_null));
            tvAge.setText(getString(R.string.age_null));
        }

        Glide.with(this)
                .load(person.getAvatarUrl())
                .placeholder(R.drawable.ic_account_box_black_48dp)
                .error(R.drawable.ic_broken_image_black_48dp)
                .into(ivAvatar);
    }

    @Override
    protected int getActivityLayoutRes() {
        return R.layout.activity_person;
    }

    @Override
    protected String getEmptyText() {
        return getString(R.string.speciality_empty);
    }

    @NonNull
    @Override
    protected RecyclerView.Adapter<?> createAdapter() {
        return new SpecialityAdapter(dataset, item -> {
        });
    }

    @Override
    public void resolveDependencies(AppComponent appComponent) {
        DaggerPersonComponent.builder()
                .appComponent(appComponent)
                .personModule(new PersonModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void beforeDestroy() {

    }
}
