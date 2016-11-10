package com.ludovic.crespeau.mangareader.interactor;

import android.content.Context;

import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.model.Chapter;
import com.ludovic.crespeau.mangareader.view.page.PageView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by crespeau on 10/11/2016.
 */

public class PageInteractorImpl implements PageInteractor {

    MangaApi mangaApi;
    private Context appContext;

    @Inject
    public PageInteractorImpl(MangaApi mangaApi, Context appContext)  {
        this.mangaApi = mangaApi;
        this.appContext = appContext;
    }

    @Override
    public void chapter(final PageView pageView, String mangaId, int chapterId) {
        mangaApi.getChapter(mangaId, chapterId).enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                Chapter chapter = response.body();
                pageView.updatePage(chapter);
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {

            }
        });
    }
}
