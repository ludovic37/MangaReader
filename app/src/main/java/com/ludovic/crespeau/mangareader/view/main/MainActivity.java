package com.ludovic.crespeau.mangareader.view.main;

import android.content.Intent;
import android.os.Bundle;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerMainComponent;
import com.ludovic.crespeau.mangareader.view.allmanga.AllMangaActivity;
import com.ludovic.crespeau.mangareader.view.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {

    MainView me = this;

    @OnClick(R.id.button1)
    public void allcard(){
        startActivity(new Intent(this, AllMangaActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void all() {

    }
}
