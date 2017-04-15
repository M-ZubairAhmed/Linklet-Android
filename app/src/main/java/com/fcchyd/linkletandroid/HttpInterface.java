package com.fcchyd.linkletandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


interface HttpInterface {

    @GET(ApiEndPoints.ENDPOINT_ALL)
    Call<LinksJavaBean>
    httpGETpageNumber(@Query("page") int pageNumber);

    @GET(ApiEndPoints.ENDPOINT_ALL)
    Call<LinksJavaBean>
    httpGETdefault();

    @GET(ApiEndPoints.ENDPOINT_FILTER)
    Call<LinksJavaBean>
    httpGETinDateRange(@Query("start") long startTime, @Query("end") long endTime);

}
