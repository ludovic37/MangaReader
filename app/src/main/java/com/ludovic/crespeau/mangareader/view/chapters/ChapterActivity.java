package com.ludovic.crespeau.mangareader.view.chapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerChapterComponent;
import com.ludovic.crespeau.mangareader.model.Chapter;
import com.ludovic.crespeau.mangareader.model.Chapters;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;
import com.ludovic.crespeau.mangareader.view.page.PageActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by crespeau on 05/11/2016.
 */

public class ChapterActivity extends BottomMenuActivity implements ChapterView {

    @Bind(R.id.recyclerViewChapter)
    RecyclerView mRecyclerViewChapter;

    String mangaId;

    private RecyclerView.Adapter mAdapter = new AdapterRecyclerViewChapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String jsonChapter = intent.getStringExtra(Constants.KEY_CHAPTERS);
        mangaId = intent.getStringExtra(Constants.KEY_MANGAID);
        //Log.d("--------",mangaId);

        //Strin JSON to List Object
        Chapters[] chapterObjectList = new Gson().fromJson(jsonChapter, Chapters[].class);
        final List<Chapters> chapterList =new ArrayList<Chapters>(Arrays.asList(chapterObjectList));

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mRecyclerViewChapter.setHasFixedSize(true);
        mRecyclerViewChapter.setLayoutManager(mLayoutManager);
        mRecyclerViewChapter.setAdapter(mAdapter);

        ((AdapterRecyclerViewChapter) mAdapter).setOnItemClickListener(new AdapterRecyclerViewChapter
                .MyClickListener() {
            @Override
            public void onItemClick(Chapters mangaList, View v) {
                Toast.makeText(ChapterActivity.this, mangaList.chapterId+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChapterActivity.this,PageActivity.class);
                intent.putExtra(Constants.KEY_CHAPTER_ID,mangaList.chapterId);
                intent.putExtra(Constants.KEY_MANGAID,mangaId);
                startActivity(intent);
            }

        });

        ((AdapterRecyclerViewChapter) mAdapter).setData(chapterList);

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerChapterComponent.builder()
                .appComponent(appComponent)
                .chapterModule(new ChapterModule())
                .build()
                .inject(this);
    }
}
