package com.fcchyd.linkletandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fcchyd.linkletandroid.Adapter.Custom1Adapter;
import com.fcchyd.linkletandroid.Model.Link;
import com.fcchyd.linkletandroid.Model.Links;
import com.fcchyd.linkletandroid.Network.HttpInterface;
import com.fcchyd.linkletandroid.Type.LinkDataType;

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
    ArrayList<LinkDataType> arrayListMod1;
    Custom1Adapter custom1Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMoreProgressB = (ProgressBar) findViewById(R.id.more_loading_progress_xml);
        listV = (ListView) findViewById(R.id.list_view_xml);
        listV.setEmptyView(findViewById(R.id.initial_loading_progress_xml));
        listJsonLink = new ArrayList<>();
        titlesArrayList = new ArrayList<>();
        arrayListMod1 = new ArrayList<>();

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

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, arrayListMod1.get(position).getUrl(), Toast.LENGTH_LONG).show();
                openLinkInBrowser(position);
            }
        });

    }

    void loadInitialPage() {
        HttpInterface HttpInterface = retrofit
                .create(HttpInterface.class);

        call = HttpInterface.httpGETpageNumber(1);

        call.enqueue(new Callback<Links>() {
            @Override
            public void onResponse(Call<Links> call, Response<Links> response) {
                try {
                    reachedLastPage = response.body().isIsLastPage();
                    currentPage = Integer.valueOf(response.body().getPage());
                    listJsonLink = response.body().getLinks();
                    for (Link link : listJsonLink) {
                        if (link.getTitle() != null && link.getDescription() != null)
//                            titlesArrayList.add(link.getTitle().trim());
                            arrayListMod1.add(new LinkDataType(link.getTitle().trim(), link.getDescription().trim(), link.getUrl().trim()));
                    }
                    custom1Adapter = new Custom1Adapter(MainActivity.this, arrayListMod1);
                    listV.setAdapter(custom1Adapter);
//                    simpleAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, titlesArrayList);
//                    listV.setAdapter(simpleAdapter);
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

        HttpInterface HttpInterface = retrofit
                .create(HttpInterface.class);

        call = HttpInterface.httpGETpageNumber(page);

        call.enqueue(new Callback<Links>() {
            @Override
            public void onResponse(Call<Links> call, Response<Links> response) {
                try {
                    listJsonLink = response.body().getLinks();
                    currentPage = Integer.valueOf(response.body().getPage());
                    reachedLastPage = response.body().isIsLastPage();
                    for (Link link : listJsonLink) {
                        if (link.getTitle() != null && link.getDescription() != null) {
//                            titlesArrayList.add(link.getTitle().trim());
                            arrayListMod1.add(new LinkDataType(link.getTitle().trim(), link.getDescription().trim(), link.getUrl().trim()));
                        }
                    }
                    loadMoreProgressB.setVisibility(View.GONE);
                    custom1Adapter.notifyDataSetChanged();
//                    simpleAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e(debugLogHeader, "error:" + e);
                }
            }

            @Override
            public void onFailure(Call<Links> call, Throwable t) {

            }
        });


    }

    void openLinkInBrowser(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrayListMod1.get(position).getUrl()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "There is'nt a single browser application that could open up this link", Toast.LENGTH_SHORT).show();
        }
    }
}
