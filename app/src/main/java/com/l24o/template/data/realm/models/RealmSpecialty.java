package com.l24o.template.data.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class RealmSpecialty extends RealmObject {

    @PrimaryKey
    private int specialityId;
    private String name;

    public RealmSpecialty() {
    }

    public RealmSpecialty(int specialityId, String name) {
        this.specialityId = specialityId;
        this.name = name;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public String getName() {
        return name;
    }

}
