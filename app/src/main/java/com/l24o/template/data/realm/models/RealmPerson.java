package com.l24o.template.data.realm.models;

import com.l24o.template.common.utils.StringUtils;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class RealmPerson extends RealmObject {

    @PrimaryKey
    private String uuid;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String avatarUrl;
    private RealmList<RealmSpecialty> specialtyList = new RealmList<>();

    public RealmPerson() {
    }

    public RealmPerson(String uuid, String firstName,
                       String lastName, Date birthday,
                       String avatarUrl) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return StringUtils.toUppercaseFirstLetter(firstName);
    }

    public String getLastName() {
        return StringUtils.toUppercaseFirstLetter(lastName);
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public RealmList<RealmSpecialty> getSpecialtyList() {
        return specialtyList;
    }

    public int getAge() {
        Calendar birthday = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        birthday.setTime(this.birthday);
        int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthday.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }
}
