package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.favoris.FavorisActivity;
import com.ludovic.crespeau.mangareader.view.favoris.FavorisModule;

import dagger.Component;

/**
 * Created by crespeau on 04/11/2016.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                FavorisModule.class
        }
)
public interface FavorisComponent {

    void inject(FavorisActivity activity);

}
