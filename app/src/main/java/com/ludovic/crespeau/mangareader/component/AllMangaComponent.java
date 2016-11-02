package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.allcard.AllMangaActivity;
import com.ludovic.crespeau.mangareader.view.allcard.AllMangaModule;

import dagger.Component;

/**
 * Created by crespeau on 02/11/2016.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                AllMangaModule.class
        }
)
public interface AllMangaComponent {
    void inject(AllMangaActivity activity);
}
