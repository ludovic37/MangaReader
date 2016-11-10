package com.ludovic.crespeau.mangareader.view.page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerPageComponent;
import com.ludovic.crespeau.mangareader.interactor.PageInteractor;
import com.ludovic.crespeau.mangareader.model.Chapter;
import com.ludovic.crespeau.mangareader.view.common.BaseActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by crespeau on 10/11/2016.
 */

public class PageActivity extends BaseActivity implements PageView{

    @Bind(R.id.imageViewPage)
    ImageView mImageViewPage;

    @OnClick(R.id.precedent)
    public void precedent(){
        if (pageActuel != 1){
            pageActuel--;
            Picasso.with(this)
                    .load(chapterPage.pages.get(pageActuel-1).url)
                    .error(R.drawable.no_image_available_large)
                    .into(mImageViewPage);
        }
    }

    @OnClick(R.id.home)
    public void home(){
        finish();

    }

    @OnClick(R.id.next)
    public void next(){
        if (pageActuel !=nbPage){
            pageActuel++;
            Picasso.with(this)
                    .load(chapterPage.pages.get(pageActuel-1).url)
                    .error(R.drawable.no_image_available_large)
                    .into(mImageViewPage);
        }

    }
    @Inject
    PageInteractor pageInteractor;

    PageView pageView = this;

    Chapter chapterPage;
    int pageActuel = 1;
    int nbPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        int chapterId = intent.getIntExtra(Constants.KEY_CHAPTER_ID,1);
        String mangaId = intent.getStringExtra(Constants.KEY_MANGAID);

        pageInteractor.chapter(this,mangaId,chapterId);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerPageComponent.builder()
                .appComponent(appComponent)
                .pageModule(new PageModule())
                .build()
                .inject(this);
    }



    @Override
    public void updatePage(Chapter chapter) {

        chapterPage = chapter;
        nbPage = chapter.pages.size();

        Picasso.with(this)
                .load(chapter.pages.get(pageActuel-1).url)
                .error(R.drawable.no_image_available_large)
                .into(mImageViewPage);
    }
}
