package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.resultActivity.ResultatActivity;
import com.ludovic.crespeau.mangareader.view.resultActivity.ResultatModule;

import dagger.Component;

/**
 * Created by crespeau on 09/11/2016.
 */


@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                ResultatModule.class
        }
)
public interface ResultatComponent {
    void inject(ResultatActivity activity);
}
