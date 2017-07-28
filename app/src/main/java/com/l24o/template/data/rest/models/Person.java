package com.l24o.template.data.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class Person {
    @SerializedName("f_name")
    private String firstName;
    @SerializedName("l_name")
    private String lastName;
    @SerializedName("birthday")
    private Date birthday;
    @SerializedName("avatr_url")
    private String avatarUrl;
    @SerializedName("specialty")
    private List<Specialty> specialtyList;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<Specialty> getSpecialtyList() {
        return specialtyList;
    }
}
