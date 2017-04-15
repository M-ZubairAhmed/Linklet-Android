package com.fcchyd.linkletandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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
    Retrofit retrofit;
    boolean reachedLastPage;
    int currentPage;
    ArrayAdapter<String> simpleAdapter;
    ProgressBar loadMoreProgressB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMoreProgressB = (ProgressBar) findViewById(R.id.more_loading_progress_xml);
        listV = (ListView) findViewById(R.id.list_view_xml);
        listV.setEmptyView(findViewById(R.id.initial_loading_progress_xml));
        listJsonLink = new ArrayList<>();
        titlesArrayList = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.links.linklet.ml")
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();

        loadInitialPage();

        listV.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int count = listV.getCount();
                int lastButCount = 1;
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listV.getLastVisiblePosition() + lastButCount == count && !reachedLastPage) {
                        loadMoreProgressB.setVisibility(View.VISIBLE);
                        loadRemainingPages(currentPage);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

    }

    void loadInitialPage() {

        HttpsInterface HttpsInterface = retrofit
                .create(HttpsInterface.class);

        call = HttpsInterface.httpGETpageNumber(1);

        call.enqueue(new Callback<Links>() {
            @Override
            public void onResponse(Call<Links> call, Response<Links> response) {
                try {
                    reachedLastPage = response.body().isIsLastPage();
                    currentPage = (int) response.body().getPage();
                    listJsonLink = response.body().getLinks();
                    for (Link link : listJsonLink) {
                        if (link.getTitle() != null)
                            titlesArrayList.add(link.getTitle().trim());
                    }
//
                    simpleAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, titlesArrayList);
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

    void loadRemainingPages(int page) {
        page = page + 1;

        HttpsInterface HttpsInterface = retrofit
                .create(HttpsInterface.class);

        call = HttpsInterface.httpGETpageNumber(page);

        call.enqueue(new Callback<Links>() {
            @Override
            public void onResponse(Call<Links> call, Response<Links> response) {
                try {
                    listJsonLink = response.body().getLinks();
                    currentPage = (int) response.body().getPage();
                    reachedLastPage = response.body().isIsLastPage();
                    for (Link link : listJsonLink) {
                        if (link.getTitle() != null) {
                            titlesArrayList.add(link.getTitle().trim());
                        }
                    }
                    loadMoreProgressB.setVisibility(View.GONE);
                    simpleAdapter.notifyDataSetChanged();
                    getSupportActionBar().setTitle(String.valueOf(currentPage));
//                    Toast.makeText(MainActivity.this, String.valueOf(currentPage), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e(debugLogHeader, "error:" + e);
                }
            }

            @Override
            public void onFailure(Call<Links> call, Throwable t) {

            }
        });

    }
}
