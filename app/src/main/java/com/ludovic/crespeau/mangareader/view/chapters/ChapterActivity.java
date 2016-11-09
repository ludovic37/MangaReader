package com.ludovic.crespeau.mangareader.view.chapters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerChapterComponent;
import com.ludovic.crespeau.mangareader.model.Chapter;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by crespeau on 05/11/2016.
 */

public class ChapterActivity extends BottomMenuActivity implements ChapterView {

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

        //Strin JSON to List Object
        Chapter[] chapterObjectList = new Gson().fromJson(jsonChapter, Chapter[].class);
        List<Chapter> chapterList =new ArrayList<Chapter>(Arrays.asList(chapterObjectList));

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
