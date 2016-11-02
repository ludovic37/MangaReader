package com.ludovic.crespeau.mangareader.interactor;

import android.content.Context;
import android.util.Log;

import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.view.allcard.AllMangaView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by crespeau on 02/11/2016.
 */

public class AllMangaInteractorImpl implements AllMangaInteractor {

    MangaApi mangaApi;
    private Context appContext;

    private String TAG = "ALL MANGA";

    @Inject
    public AllMangaInteractorImpl(MangaApi mangaApi, Context appContext)  {
        this.mangaApi = mangaApi;
        this.appContext = appContext;
    }

    @Override
    public void allManga(final AllMangaView me) {
        mangaApi.getMangaList().enqueue(new Callback<List<MangaList>>() {
            @Override
            public void onResponse(Call<List<MangaList>> call, Response<List<MangaList>> response) {
                Log.d(TAG,"Good");
                List<MangaList> mangaList = response.body();
                me.update(mangaList);
            }

            @Override
            public void onFailure(Call<List<MangaList>> call, Throwable t) {
                Log.d(TAG,"Fail");
            }
        });
    }
}
