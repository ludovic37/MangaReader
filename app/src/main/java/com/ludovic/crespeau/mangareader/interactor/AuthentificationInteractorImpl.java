package com.ludovic.crespeau.mangareader.interactor;



import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.view.main.MainView;

import javax.inject.Inject;

/**
 * Created by Ludovic on 16/06/16.
 */
public class AuthentificationInteractorImpl implements AuthentificationInteractor {

    MangaApi mangaApi;

    @Inject public AuthentificationInteractorImpl(MangaApi mangaApi)  {
        this.mangaApi = mangaApi;
    }

    @Override
    public void authenticate(String username, long userid, String accessToken, String accessTokenSecret, final MainView mainView) {

    }

    @Override
    public void notification(final MainView mainView) {

    }
}
