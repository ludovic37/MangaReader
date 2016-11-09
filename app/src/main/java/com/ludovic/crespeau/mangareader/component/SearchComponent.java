package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.search.SearchActivity;
import com.ludovic.crespeau.mangareader.view.search.SearchModule;

import dagger.Component;

/**
 * Created by crespeau on 05/11/2016.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                SearchModule.class
        }
)
public interface SearchComponent {
    void inject(SearchActivity activity);
}
