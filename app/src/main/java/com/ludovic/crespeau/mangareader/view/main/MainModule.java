package com.ludovic.crespeau.mangareader.view.main;

import dagger.Module;

/**
 * Created by Ludovic on 24/09/16.
 */
@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }
}
