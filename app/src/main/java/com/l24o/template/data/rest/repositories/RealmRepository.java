package com.l24o.template.data.rest.repositories;

import android.support.annotation.NonNull;

import com.l24o.template.data.realm.models.RealmPerson;
import com.l24o.template.data.realm.models.RealmSpecialty;
import com.l24o.template.data.rest.models.ApiResponse;
import com.l24o.template.data.rest.models.Person;
import com.l24o.template.data.rest.models.Specialty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class RealmRepository extends Repository {

    private final Realm realm;

    @Inject
    public RealmRepository(Realm realm) {
        this.realm = realm;
    }

    public void close() {
        realm.close();
    }

    public Observable<Boolean> isCached() {
        return Observable.just(realm
                .where(RealmPerson.class)
                .count() > 0);
    }

    public Observable<List<RealmPerson>> fetchPersons() {
        return realm
                .where(RealmPerson.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .first()
                .flatMap(realmPeople -> Observable.just(realm.copyFromRealm(realmPeople)));
    }

    public Observable<List<RealmPerson>> fetchPersonBySpecialityId(int specId) {
        return realm
                .where(RealmPerson.class)
                .equalTo("specialtyList.specialityId", specId)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .first()
                .flatMap(realmPeople -> Observable.just(realm.copyFromRealm(realmPeople)));
    }

    public Observable<List<RealmSpecialty>> fetchSpec() {
        return realm
                .where(RealmSpecialty.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .first()
                .flatMap(values -> Observable.just(realm.copyFromRealm(values)));
    }

    public Observable<Boolean> saveApiResponse(ApiResponse apiResponse) {
        return Observable.create(subscriber ->
                realm.executeTransactionAsync(realm -> {
                            realm.deleteAll();
                            realm.copyToRealm(parseSpec(apiResponse));
                            realm.copyToRealm(parseApiResponse(realm, apiResponse));
                        },
                        () -> subscriber.onNext(true),
                        subscriber::onError));
    }

    @NonNull
    public Observable<RealmPerson> fetchPersonById(String uuid) {
        return realm
                .where(RealmPerson.class)
                .equalTo("uuid", uuid)
                .findFirst()
                .asObservable();
    }

    @NonNull
    private List<RealmPerson> parseApiResponse(Realm realm, ApiResponse apiResponse) {
        List<RealmPerson> persons = new ArrayList<>();
        for (Person person : apiResponse.getPersonList()) {
            RealmPerson realmPerson = new RealmPerson(
                    UUID.randomUUID().toString(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getBirthday(),
                    person.getAvatarUrl()
            );
            realmPerson.getSpecialtyList()
                    .addAll(getBySpecialtyList(realm, person.getSpecialtyList()));
            persons.add(realmPerson);
        }
        return persons;
    }

    private Collection<RealmSpecialty> parseSpec(ApiResponse apiResponse) {
        HashMap<Integer, RealmSpecialty> specialityes = new HashMap<>();
        for (Person person : apiResponse.getPersonList()) {
            for (Specialty specialty : person.getSpecialtyList()) {
                specialityes.put(specialty.getSpecialityId(), new RealmSpecialty(specialty.getSpecialityId(), specialty.getName()));
            }
        }
        return specialityes.values();
    }

    private RealmSpecialty fetchSpecById(Realm realm, int specId) {
        return realm
                .where(RealmSpecialty.class)
                .equalTo("specialityId", specId)
                .findFirst();
    }

    private List<RealmSpecialty> getBySpecialtyList(Realm realm, List<Specialty> specialties) {
        List<RealmSpecialty> result = new ArrayList<>();
        for (Specialty specialty : specialties) {
            result.add(fetchSpecById(realm, specialty.getSpecialityId()));
        }
        return result;
    }


}
