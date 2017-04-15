package com.fcchyd.linkletandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


interface HttpsInterface {

    @GET(ApiEndPoints.ENDPOINT_ALL)
    Call<Links>
    httpGETpageNumber(@Query("page") int pageNumber);

    @GET(ApiEndPoints.ENDPOINT_ALL)
    Call<Links>
    httpGETdefault();

    @GET(ApiEndPoints.ENDPOINT_FILTER)
    Call<Links>
    httpGETinDateRange(@Query("start") long startTime, @Query("end") long endTime);

}
