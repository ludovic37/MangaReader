package com.ludovic.crespeau.mangareader.view.manga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerMangaComponent;
import com.ludovic.crespeau.mangareader.interactor.MangaInteractor;
import com.ludovic.crespeau.mangareader.model.Manga;
import com.ludovic.crespeau.mangareader.view.common.BaseActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by crespeau on 02/11/2016.
 */

public class MangaActivity extends BaseActivity implements MangaView {

    @Bind(R.id.textViewNameManga) TextView mTextViewNameManga;
    @Bind(R.id.textViewArtistManga) TextView mTextViewArtistManga;
    @Bind(R.id.textViewAuthorManga) TextView mTextViewAuthorManga;
    @Bind(R.id.textViewGenreManga) TextView mTextViewGenreManga;
    @Bind(R.id.textViewInfosManga) TextView mTextViewInfosManga;
    @Bind(R.id.textViewStatusManga) TextView mTextViewStatusManga;
    @Bind(R.id.textViewYearManga) TextView mTextViewYearManga;

    @Bind(R.id.imageViewCoverManga) ImageView mImageViewCoverManga;

    @Inject
    MangaInteractor mangaInteractor;


    MangaView me = this;
    private String mangaid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mangaid = intent.getStringExtra(Constants.KEY_MANGAID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpViews();
    }

    private void setUpViews() {


        mangaInteractor.manga(me,mangaid);

    }

    @Override
    public void update(Manga manga) {

        mTextViewNameManga.setText(manga.name);
        mTextViewArtistManga.setText(manga.artist.toString());
        mTextViewAuthorManga.setText(manga.author.toString());
        mTextViewGenreManga.setText(manga.genres.toString());
        mTextViewInfosManga.setText(manga.info);
        mTextViewStatusManga.setText(manga.status);
        mTextViewYearManga.setText(manga.yearOfRelease+"");

        Picasso.with(this)
                .load(manga.cover)
                .fit()
                .into(mImageViewCoverManga);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMangaComponent.builder()
                .appComponent(appComponent)
                .mangaModule(new MangaModule())
                .build()
                .inject(this);

    }
}
