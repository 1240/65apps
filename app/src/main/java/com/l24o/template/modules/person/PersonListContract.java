package com.l24o.template.modules.person;

import com.l24o.template.common.mvp.MvpContract;
import com.l24o.template.data.realm.models.RealmPerson;

/**
 * @author Alexander Popov on 18/07/2017.
 */

public interface PersonListContract {

    interface IPersonListView extends MvpContract.IListView<RealmPerson> {
        void navigateToPerson(RealmPerson person);
    }

    interface IPersonListPresenter extends MvpContract.IListPresenter<RealmPerson, IPersonListView> {
        void onItemClick(RealmPerson person);
        void init(int id);
    }

}
