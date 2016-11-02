package com.ludovic.crespeau.mangareader.interactor;


import com.ludovic.crespeau.mangareader.view.main.MainView;

/**
 * Created by Ludovic on 16/06/16.
 */
public interface AuthentificationInteractor {

    void authenticate(String username, long userid, String accessToken, String accessTokenSecret, MainView mainView);
    void notification(MainView mainView);

}
