package com.ludovic.crespeau.mangareader.view.chapters;

import android.os.Bundle;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerChapterComponent;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;

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
    protected void setupComponent(AppComponent appComponent) {
        DaggerChapterComponent.builder()
                .appComponent(appComponent)
                .chapterModule(new ChapterModule())
                .build()
                .inject(this);
    }
}
