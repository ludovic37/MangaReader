package com.ludovic.crespeau.mangareader.component;

import com.ludovic.crespeau.mangareader.ActivityScope;
import com.ludovic.crespeau.mangareader.view.page.PageActivity;
import com.ludovic.crespeau.mangareader.view.page.PageModule;

import dagger.Component;

/**
 * Created by crespeau on 10/11/2016.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                PageModule.class
        }
)
public interface PageComponent {




        void inject(PageActivity activity);

}
