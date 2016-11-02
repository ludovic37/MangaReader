package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.App;
import com.ludovic.crespeau.mangareader.api.AuthenticatedInterceptor;
import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.interactor.AllMangaInteractor;
import com.ludovic.crespeau.mangareader.interactor.AuthentificationInteractor;
import com.ludovic.crespeau.mangareader.modules.AppModule;
import com.ludovic.crespeau.mangareader.modules.InteractorsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by crespeau on 01/11/2016.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                InteractorsModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    MangaApi getMangaApi();
    AuthentificationInteractor getAuthentificationInteractor();
    AllMangaInteractor getAllMangaInteractor();
    //NearbyInteractor getNearbyInteractor();
    AuthenticatedInterceptor getAuthenticatedInterceptor();
}
