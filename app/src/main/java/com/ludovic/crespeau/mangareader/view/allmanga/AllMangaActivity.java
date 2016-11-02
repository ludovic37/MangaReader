package com.ludovic.crespeau.mangareader.view.allmanga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerAllMangaComponent;
import com.ludovic.crespeau.mangareader.interactor.AllMangaInteractor;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.view.common.BaseActivity;
import com.ludovic.crespeau.mangareader.view.manga.MangaActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by crespeau on 02/11/2016.
 */

public class AllMangaActivity extends BaseActivity implements AllMangaView {

    @Bind(R.id.reciclerViewAllManga)
    RecyclerView mReclerViewAllManga;

    @Inject
    AllMangaInteractor allMangaInteractor;

    AllMangaView allMangaView = this;
    private RecyclerView.Adapter mAdapter = new AdapterRecyclerViewAllManga(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmanga_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpViews();

    }

    private void setUpViews() {

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mReclerViewAllManga.setHasFixedSize(true);
        mReclerViewAllManga.setLayoutManager(mLayoutManager);
        mReclerViewAllManga.setAdapter(mAdapter);

        ((AdapterRecyclerViewAllManga) mAdapter).setOnItemClickListener(new AdapterRecyclerViewAllManga
                .MyClickListener() {
            @Override
            public void onItemClick(MangaList mangaList, View v) {

                Toast.makeText(AllMangaActivity.this, mangaList.mangaId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AllMangaActivity.this,MangaActivity.class);
                intent.putExtra(Constants.KEY_MANGAID,mangaList.mangaId);
                startActivity(intent);
            }
        });

        allMangaInteractor.allManga(allMangaView);
    }


    @Override
    public void update(List<MangaList> mangaLists) {

        ((AdapterRecyclerViewAllManga) mAdapter).setData(mangaLists);

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerAllMangaComponent.builder()
                .appComponent(appComponent)
                .allMangaModule(new AllMangaModule())
                .build()
                .inject(this);
    }
}
