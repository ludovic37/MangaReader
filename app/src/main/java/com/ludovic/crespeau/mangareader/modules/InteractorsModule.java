package com.ludovic.crespeau.mangareader.modules;

import android.content.Context;


import com.ludovic.crespeau.mangareader.api.AuthenticatedInterceptor;
import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.api.RestApiAdapter;
import com.ludovic.crespeau.mangareader.interactor.AllMangaInteractor;
import com.ludovic.crespeau.mangareader.interactor.AllMangaInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.AuthentificationInteractor;
import com.ludovic.crespeau.mangareader.interactor.AuthentificationInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.ChapterInteractor;
import com.ludovic.crespeau.mangareader.interactor.ChapterInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.FavorisInteractor;
import com.ludovic.crespeau.mangareader.interactor.FavorisInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.MangaInteractor;
import com.ludovic.crespeau.mangareader.interactor.MangaInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.PageInteractor;
import com.ludovic.crespeau.mangareader.interactor.PageInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.ResultatInteractor;
import com.ludovic.crespeau.mangareader.interactor.ResultatInteractorImpl;
import com.ludovic.crespeau.mangareader.interactor.SearchInteractor;
import com.ludovic.crespeau.mangareader.interactor.SearchInteractorImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Ludovic on 16/06/16.
 */
@Module
public class InteractorsModule {

    @Provides
    public AuthenticatedInterceptor provideAuthenticatedInterceptor(Context appContext)   {
        return new AuthenticatedInterceptor(appContext);
    }

    @Provides
    public AuthentificationInteractor provideAuthentificationInteractor(MangaApi aintroApi) {
        return new AuthentificationInteractorImpl(aintroApi);
    }

    @Provides
    public AllMangaInteractor provideAllMangaInteractor(MangaApi aintroApi, Context appContext){
        return new AllMangaInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public MangaInteractor provideMangaInteractor(MangaApi aintroApi, Context appContext){
        return new MangaInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public ChapterInteractor provideChapterInteractor(MangaApi aintroApi, Context appContext){
        return new ChapterInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public FavorisInteractor provideFavorisInteractor(MangaApi aintroApi, Context appContext){
        return new FavorisInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public SearchInteractor provideSearchInteractor(MangaApi aintroApi, Context appContext){
        return new SearchInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public PageInteractor providePageInteractor(MangaApi aintroApi, Context appContext){
        return new PageInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public ResultatInteractor provideResultatInteractor(MangaApi aintroApi, Context appContext){
        return new ResultatInteractorImpl(aintroApi, appContext);
    }

    @Provides
    public MangaApi provideMyApi(Retrofit retrofit) {
        return retrofit.create(MangaApi.class);
    }

    @Provides
    public Retrofit provideRestAdapter(AuthenticatedInterceptor authenticatedInterceptor) {
        return new RestApiAdapter(authenticatedInterceptor).getInstance();
    }
}
