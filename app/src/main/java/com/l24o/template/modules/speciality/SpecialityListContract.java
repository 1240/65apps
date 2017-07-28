package com.l24o.template.modules.speciality;

import com.l24o.template.common.mvp.MvpContract;
import com.l24o.template.data.realm.models.RealmSpecialty;

/**
 * @author Alexander Popov on 18/07/2017.
 */

public interface SpecialityListContract {

    interface ISpecialityListView extends MvpContract.IListView<RealmSpecialty> {
        void navigateToPersons(RealmSpecialty specialty);
    }

    interface ISpecialityListPresenter extends MvpContract.IListPresenter<RealmSpecialty, ISpecialityListView> {
        void onItemClick(RealmSpecialty item);
    }

}
