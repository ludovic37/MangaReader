package com.ludovic.crespeau.mangareader.modules;

import android.app.Application;
import android.content.Context;


import com.ludovic.crespeau.mangareader.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    public Context provideContext(Application app) {
        return app.getApplicationContext();
    }

}
