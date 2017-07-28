package com.l24o.template.modules.person.info;

import com.l24o.template.common.mvp.MvpContract;
import com.l24o.template.data.realm.models.RealmPerson;
import com.l24o.template.data.realm.models.RealmSpecialty;

/**
 * @author Alexander Popov on 28/07/2017.
 */

public interface PersonContract {

    interface IPersonView extends MvpContract.IListView<RealmSpecialty> {
        void fillView(RealmPerson person);
    }

    interface IPersonPresenter extends MvpContract.IListPresenter<RealmSpecialty, IPersonView> {
        void init(String personId);
    }

}
