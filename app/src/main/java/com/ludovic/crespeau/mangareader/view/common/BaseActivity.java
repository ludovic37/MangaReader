package com.ludovic.crespeau.mangareader.view.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ludovic.crespeau.mangareader.App;
import com.ludovic.crespeau.mangareader.component.AppComponent;


/**
 * Created by Ludovic on 16/06/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    //private static final String TAG = NearbyActivity.class.getSimpleName();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent((AppComponent) App.get(this).component());
    }

    protected abstract void setupComponent(AppComponent appComponent);

}
