package com.ludovic.crespeau.mangareader;

import android.app.Application;
import android.content.Context;

import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerAppComponent;
import com.ludovic.crespeau.mangareader.modules.AppModule;

/**
 * Created by crespeau on 01/11/2016.
 */

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
