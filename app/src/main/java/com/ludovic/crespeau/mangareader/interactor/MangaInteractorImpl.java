package com.ludovic.crespeau.mangareader.interactor;

import android.content.Context;
import android.util.Log;

import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.model.Manga;
import com.ludovic.crespeau.mangareader.view.manga.MangaView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by crespeau on 02/11/2016.
 */

public class MangaInteractorImpl implements MangaInteractor {

    MangaApi mangaApi;
    private Context appContext;

    private String TAG = "MANGA";

    @Inject
    public MangaInteractorImpl(MangaApi mangaApi, Context appContext)  {
        this.mangaApi = mangaApi;
        this.appContext = appContext;
    }

    @Override
    public void manga(final MangaView mangaView, String mangaid) {
        mangaApi.getManga(mangaid).enqueue(new Callback<Manga>() {
            @Override
            public void onResponse(Call<Manga> call, Response<Manga> response) {
                Log.d(TAG,"Good");
                Manga manga = response.body();
                mangaView.update(manga);
            }

            @Override
            public void onFailure(Call<Manga> call, Throwable t) {
                Log.d(TAG,"Fail");
            }
        });
    }
}
