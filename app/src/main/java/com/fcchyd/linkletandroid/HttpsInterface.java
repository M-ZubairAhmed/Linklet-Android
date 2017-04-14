package com.fcchyd.linkletandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


interface HttpsInterface {

    @GET(ApiEndPoints.END_POINT_ALL)
    Call<Links>
    httpGETpageNumber(@Query("page") int pageNumber);

    @GET(ApiEndPoints.END_POINT_ALL)
    Call<Links>
    httpGETdefault();

    @GET(ApiEndPoints.END_POINT_FILTER)
    Call<Links>
    httpGETinRange(@Query("start") long startTime, @Query("end") long endTime);

}
