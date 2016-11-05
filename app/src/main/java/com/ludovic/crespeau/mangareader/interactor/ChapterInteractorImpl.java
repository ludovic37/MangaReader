package com.ludovic.crespeau.mangareader.interactor;

import android.content.Context;

import com.ludovic.crespeau.mangareader.api.MangaApi;

import javax.inject.Inject;

/**
 * Created by crespeau on 05/11/2016.
 */

public class ChapterInteractorImpl implements ChapterInteractor {

    MangaApi mangaApi;
    private Context appContext;

    private String TAG = "MANGA";

    @Inject
    public ChapterInteractorImpl(MangaApi mangaApi, Context appContext)  {
        this.mangaApi = mangaApi;
        this.appContext = appContext;
    }

}
