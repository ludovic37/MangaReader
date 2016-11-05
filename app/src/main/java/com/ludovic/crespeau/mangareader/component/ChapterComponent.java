package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.chapters.ChapterActivity;
import com.ludovic.crespeau.mangareader.view.chapters.ChapterModule;

import dagger.Component;

/**
 * Created by crespeau on 05/11/2016.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                ChapterModule.class
        }
)
public interface ChapterComponent {

    void inject(ChapterActivity activity);

}
