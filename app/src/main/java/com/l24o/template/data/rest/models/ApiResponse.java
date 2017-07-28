package com.l24o.template.data.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class ApiResponse {
    @SerializedName("response")
    private List<Person> personList;

    public List<Person> getPersonList() {
        return personList;
    }
}
