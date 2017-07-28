package com.l24o.template.data.rest.datasource;

import com.l24o.template.data.rest.models.ApiResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public interface MainDataSource {

    @GET("testTask.json")
    public Observable<ApiResponse> getInfo();

}
