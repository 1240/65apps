package com.l24o.template.data.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class Specialty {

    @SerializedName("specialty_id")
    private int specialityId;
    @SerializedName("name")
    private String name;

    public int getSpecialityId() {
        return specialityId;
    }

    public String getName() {
        return name;
    }
}
