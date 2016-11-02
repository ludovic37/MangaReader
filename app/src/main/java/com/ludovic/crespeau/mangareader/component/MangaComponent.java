package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.manga.MangaActivity;
import com.ludovic.crespeau.mangareader.view.manga.MangaModule;

import dagger.Component;

/**
 * Created by crespeau on 02/11/2016.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                MangaModule.class
        }
)
public interface MangaComponent {

    void inject(MangaActivity activity);
}
