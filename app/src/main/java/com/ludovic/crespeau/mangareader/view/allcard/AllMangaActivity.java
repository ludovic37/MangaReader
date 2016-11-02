package com.ludovic.crespeau.mangareader.view.allcard;

import android.os.Bundle;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerAllMangaComponent;
import com.ludovic.crespeau.mangareader.interactor.AllMangaInteractor;
import com.ludovic.crespeau.mangareader.view.common.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by crespeau on 02/11/2016.
 */

public class AllMangaActivity extends BaseActivity implements AllMangaView {

    @Inject
    AllMangaInteractor allMangaInteractor;

    AllMangaView nearbyView = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmanga_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpViews();

    }

    private void setUpViews() {
        allMangaInteractor.allManga(nearbyView);
    }


    @Override
    public void update() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerAllMangaComponent.builder()
                .appComponent(appComponent)
                .allMangaModule(new AllMangaModule())
                .build()
                .inject(this);
    }
}
