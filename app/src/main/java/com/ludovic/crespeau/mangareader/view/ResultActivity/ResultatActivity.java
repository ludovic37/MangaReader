package com.ludovic.crespeau.mangareader.view.resultActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerResultatComponent;
import com.ludovic.crespeau.mangareader.interactor.ResultatInteractor;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.view.allmanga.AdapterRecyclerViewAllManga;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;
import com.ludovic.crespeau.mangareader.view.manga.MangaActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by crespeau on 09/11/2016.
 */

public class ResultatActivity extends BottomMenuActivity implements ResultatView {

    @Bind(R.id.recyclerViewResultatManga)
    RecyclerView mReclerViewResultatManga;

    @Inject
    ResultatInteractor resultatInteractor;

    ResultatView resultatMangaView = this;
    private RecyclerView.Adapter mAdapter = new AdapterRecyclerViewAllManga(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String jsonSearch = intent.getStringExtra(Constants.KEY_SEARCH);

        //Strin JSON to List Object
        MangaList[] chapterObjectList = new Gson().fromJson(jsonSearch, MangaList[].class);
        List<MangaList> resultatList =new ArrayList<MangaList>(Arrays.asList(chapterObjectList));

        mReclerViewResultatManga.setHasFixedSize(true);

        final StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        mReclerViewResultatManga.setLayoutManager(mLayoutManager);
        mReclerViewResultatManga.setAdapter(mAdapter);

        ((AdapterRecyclerViewAllManga) mAdapter).setOnItemClickListener(new AdapterRecyclerViewAllManga
                .MyClickListener() {
            @Override
            public void onItemClick(MangaList mangaList, View v) {

                Toast.makeText(ResultatActivity.this, mangaList.mangaId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResultatActivity.this,MangaActivity.class);
                intent.putExtra(Constants.KEY_MANGAID,mangaList.mangaId);
                startActivity(intent);
            }
        });

        ((AdapterRecyclerViewAllManga) mAdapter).setData(resultatList);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerResultatComponent.builder()
                .appComponent(appComponent)
                .resultatModule(new ResultatModule())
                .build()
                .inject(this);
    }
}
