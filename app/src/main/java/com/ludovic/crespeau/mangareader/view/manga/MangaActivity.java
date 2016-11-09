package com.ludovic.crespeau.mangareader.view.manga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerMangaComponent;
import com.ludovic.crespeau.mangareader.interactor.MangaInteractor;
import com.ludovic.crespeau.mangareader.model.Manga;
import com.ludovic.crespeau.mangareader.model.MangaId;
import com.ludovic.crespeau.mangareader.view.chapters.ChapterActivity;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by crespeau on 02/11/2016.
 */

public class MangaActivity extends BottomMenuActivity implements MangaView {

    @Bind(R.id.textViewNameManga)
    TextView mTextViewNameManga;
    @Bind(R.id.textViewArtistManga)
    TextView mTextViewArtistManga;
    @Bind(R.id.textViewAuthorManga)
    TextView mTextViewAuthorManga;
    @Bind(R.id.textViewGenreManga)
    TextView mTextViewGenreManga;
    @Bind(R.id.textViewInfosManga)
    TextView mTextViewInfosManga;
    @Bind(R.id.textViewStatusManga)
    TextView mTextViewStatusManga;
    @Bind(R.id.textViewYearManga)
    TextView mTextViewYearManga;

    @Bind(R.id.imageViewCoverManga)
    ImageView mImageViewCoverManga;
    @Bind(R.id.imageViewFavoris)
    ImageView mImageViewFavoris;

    SharedPreferences sharedPreferences;

    Manga myManga;

    @OnClick(R.id.imageViewFavoris)
    public void favoris() {

        Gson gson = new Gson();
        MangaId myMangaId = new MangaId();
        myMangaId.mangaId = mangaid;
        myMangaId.manga = myManga;

        //Object to String JSON
        String json = gson.toJson(myMangaId);
        Log.d("JSON", json);

        //Strin JSON to Object
        //Manga manga = gson.fromJson(json,Manga.class);
        //Log.d("Object",manga.name);


        sharedPreferences = getSharedPreferences(Constants.KEY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> favorisSet = sharedPreferences.getStringSet(Constants.KEY_FAVORIS, new TreeSet<String>());

        boolean test = false;

        for (String temp : favorisSet) {
            if (temp.compareTo(json) == 0) {
                Log.d("Favoris", "deja Present");
                favorisSet.remove(json);
                test = true;
                mImageViewFavoris.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_off));
                break;
            }
        }

        if (!test) {
            Log.d("Favoris", "New favoris");
            favorisSet.add(json);
            editor.putStringSet("favoris", favorisSet);
            editor.apply();

            mImageViewFavoris.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on));
        }


    }

    @OnClick(R.id.buttonChapterManga)
    public void chapterManga() {

        //Object to String JSON
        Gson gson = new Gson();
        String jsonChapter = gson.toJson(myManga.chapters);

        Intent intent = new Intent(this, ChapterActivity.class);
        intent.putExtra(Constants.KEY_CHAPTERS,jsonChapter);
        startActivity(intent);
    }

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


        mangaInteractor.manga(me, mangaid);

    }

    @Override
    public void update(Manga manga) {

        myManga = manga;

        mTextViewNameManga.setText(manga.name);

        List<String> artistList = manga.artist;
        String artist = "Artist: ";
        for (int i = 0; i < artistList.size(); i++)
            artist += artistList.get(i) + ", ";
        mTextViewArtistManga.setText(artist);

        List<String> authorList = manga.author;
        String author = "Author: ";
        for (int i = 0; i < authorList.size(); i++)
            author += authorList.get(i) + ", ";
        mTextViewAuthorManga.setText(author);

        List<String> genresList = manga.genres;
        String genres = "Genres: ";
        for (int i = 0; i < genresList.size(); i++)
            genres += genresList.get(i) + ", ";
        mTextViewGenreManga.setText(genres);

        mTextViewInfosManga.setText(manga.info);
        mTextViewStatusManga.setText(manga.status);
        mTextViewYearManga.setText(manga.yearOfRelease + "");

        Picasso.with(this)
                .load(manga.cover)
                .fit()
                .into(mImageViewCoverManga);


        Gson gson = new Gson();
        MangaId myMangaId = new MangaId();
        myMangaId.mangaId = mangaid;
        myMangaId.manga = myManga;

        //Object to String JSON
        String json = gson.toJson(myMangaId);
        sharedPreferences = getSharedPreferences(Constants.KEY_PREF, MODE_PRIVATE);

        Set<String> favorisSet = sharedPreferences.getStringSet(Constants.KEY_FAVORIS, new TreeSet<String>());

        for (String temp : favorisSet) {
            if (temp.compareTo(json) == 0) {

                mImageViewFavoris.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on));
                break;
            }
        }

        Log.d("Manga", manga.toString());
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
