package com.l24o.template.data.rest.repositories;

import com.l24o.template.data.rest.datasource.MainDataSource;
import com.l24o.template.data.rest.models.ApiResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class MainRepository extends Repository {

    private final MainDataSource mainDataSource;

    @Inject
    public MainRepository(MainDataSource mainDataSource) {
        this.mainDataSource = mainDataSource;
    }

    public Observable<ApiResponse> getInfo() {
        return mainDataSource.getInfo()
                .compose(applySchedulers());
    }

}
