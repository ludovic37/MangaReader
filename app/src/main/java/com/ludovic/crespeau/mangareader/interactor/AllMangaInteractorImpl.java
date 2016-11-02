package com.ludovic.crespeau.mangareader.interactor;

import android.content.Context;

import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.view.allcard.AllMangaView;

import javax.inject.Inject;

/**
 * Created by crespeau on 02/11/2016.
 */

public class AllMangaInteractorImpl implements AllMangaInteractor {

    MangaApi mangaApi;
    private Context appContext;

    @Inject
    public AllMangaInteractorImpl(MangaApi mangaApi, Context appContext)  {
        this.mangaApi = mangaApi;
        this.appContext = appContext;
    }

    @Override
    public void allManga(AllMangaView me) {

    }
}
