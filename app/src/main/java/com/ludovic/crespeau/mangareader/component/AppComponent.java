package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.App;
import com.ludovic.crespeau.mangareader.api.AuthenticatedInterceptor;
import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.interactor.AllMangaInteractor;
import com.ludovic.crespeau.mangareader.interactor.AuthentificationInteractor;
import com.ludovic.crespeau.mangareader.interactor.ChapterInteractor;
import com.ludovic.crespeau.mangareader.interactor.FavorisInteractor;
import com.ludovic.crespeau.mangareader.interactor.MangaInteractor;
import com.ludovic.crespeau.mangareader.interactor.PageInteractor;
import com.ludovic.crespeau.mangareader.interactor.ResultatInteractor;
import com.ludovic.crespeau.mangareader.interactor.SearchInteractor;
import com.ludovic.crespeau.mangareader.model.Chapter;
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
    MangaInteractor getMangaInteractor();
    FavorisInteractor getFavorisInteractor();
    ChapterInteractor getChapterInteractor();
    ResultatInteractor getResultatInteractor();
    SearchInteractor getSearchInteractor();
    PageInteractor getPageInteractor();
    AuthenticatedInterceptor getAuthenticatedInterceptor();
}
