package com.fcchyd.linkletandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String debugLogHeader = "Linklet Debug Message";
    Call<Links> call;
    List<Link> listJsonLink;
    ListView listV;
    List<String> titlesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listV = (ListView) findViewById(R.id.list_view_xml);
        listV.setEmptyView(findViewById(R.id.loading_progress_xml));
        listJsonLink = new ArrayList<>();
        titlesArrayList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.links.linklet.ml")
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();

        HttpsInterface HttpsInterface = retrofit
                .create(HttpsInterface.class);

        call = HttpsInterface.httpGETpageNumber(1);

        call.enqueue(new Callback<Links>() {
            @Override
            public void onResponse(Call<Links> call, Response<Links> response) {
                try {
                    listJsonLink = response.body().getLinks();
                    for (Link link : listJsonLink) {
                        titlesArrayList.add(link.getTitle());
                    }

                    ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, titlesArrayList);
                    listV.setAdapter(simpleAdapter);
                } catch (Exception e) {
                    Log.e("erro", "" + e);
                }
            }

            @Override
            public void onFailure(Call<Links> call, Throwable t) {

            }
        });


    }
}
