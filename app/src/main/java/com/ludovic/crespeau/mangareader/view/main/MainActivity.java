package com.ludovic.crespeau.mangareader.view.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

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
    public void start(){

        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (network==null || !network.isConnected()) {
            // Le périphérique n'est pas connecté à Internet
            Toast.makeText(MainActivity.this,"Pas de connection Internet",Toast.LENGTH_SHORT).show();
        }
        else{
            // Le périphérique est connecté à Internet
            startActivity(new Intent(MainActivity.this, AllMangaActivity.class));
        }

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

    private void testNetwork(){
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (network==null || !network.isConnected()) {
            // Le périphérique n'est pas connecté à Internet
            Toast.makeText(MainActivity.this,"Pas de connection Internet",Toast.LENGTH_SHORT).show();
        }
        else{
            // Le périphérique est connecté à Internet
            startActivity(new Intent(MainActivity.this, AllMangaActivity.class));
        }
    }


}
