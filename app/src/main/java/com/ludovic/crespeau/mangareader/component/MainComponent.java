package com.ludovic.crespeau.mangareader.component;


import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.main.MainActivity;
import com.ludovic.crespeau.mangareader.view.main.MainModule;

import dagger.Component;

/**
 * Created by Ludovic on 24/09/16.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                MainModule.class
        }
)
public interface MainComponent {
    void inject(MainActivity activity);
}
