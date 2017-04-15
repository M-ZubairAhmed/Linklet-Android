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
    List<Link> arraylistLink;
    ListView linksListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linksListV = (ListView) findViewById(R.id.list_view_xml);
        linksListV.setEmptyView(findViewById(R.id.loading_progress_xml));
        arraylistLink = new ArrayList<>();

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
                    arraylistLink = response.body().getLinks();

                    String[] simpletTitlesArray = new String[arraylistLink.size()];
                    for (int i = 0; i < simpletTitlesArray.length; i++) {
                        simpletTitlesArray[i] = arraylistLink.get(i).getTitle();
                    }
                    ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, simpletTitlesArray);
                    linksListV.setAdapter(simpleAdapter);
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
