package com.ludovic.crespeau.mangareader.view.favoris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerFavorisComponent;
import com.ludovic.crespeau.mangareader.model.MangaId;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.view.allmanga.AdapterRecyclerViewAllManga;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;
import com.ludovic.crespeau.mangareader.view.manga.MangaActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by crespeau on 04/11/2016.
 */

public class FavorisActivity extends BottomMenuActivity implements FavorisView {

    SharedPreferences sharedPreferences;

    @Bind(R.id.recyclerViewFavoris)
    RecyclerView mReclerViewFavoris;


    private RecyclerView.Adapter mAdapter = new AdapterRecyclerViewFavoris(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoris_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getSharedPreferences(Constants.KEY_PREF, MODE_PRIVATE);
        Set<String> favorisSet = sharedPreferences.getStringSet(Constants.KEY_FAVORIS, new TreeSet<String>());

        List<MangaId> mangaIds = new ArrayList<>();
        Gson gson = new Gson();

        for (String temp : favorisSet){
            MangaId mangaIdTemp = gson.fromJson(temp,MangaId.class);
            mangaIds.add(mangaIdTemp);
        }

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        mReclerViewFavoris.setHasFixedSize(true);
        mReclerViewFavoris.setLayoutManager(mLayoutManager);
        mReclerViewFavoris.setAdapter(mAdapter);

        ((AdapterRecyclerViewFavoris) mAdapter).setOnItemClickListener(new AdapterRecyclerViewFavoris
                .MyClickListener() {
            @Override
            public void onItemClick(MangaId mangaList, View v) {
                Toast.makeText(FavorisActivity.this, mangaList.mangaId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FavorisActivity.this,MangaActivity.class);
                intent.putExtra(Constants.KEY_MANGAID,mangaList.mangaId);
                startActivity(intent);
            }

        });

        ((AdapterRecyclerViewFavoris) mAdapter).setData(mangaIds);

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerFavorisComponent.builder()
                .appComponent(appComponent)
                .favorisModule(new FavorisModule())
                .build()
                .inject(this);
    }
}
