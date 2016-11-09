package com.ludovic.crespeau.mangareader.view.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.util.Log;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.view.allmanga.AllMangaActivity;
import com.ludovic.crespeau.mangareader.view.favoris.FavorisActivity;
import com.ludovic.crespeau.mangareader.view.search.SearchActivity;

import butterknife.Bind;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by jeromeheissler on 21/07/2016.
 */

public abstract class BottomMenuActivity extends BaseActivity implements BottomNavigation.OnMenuItemSelectionListener {



    @Bind(R.id.BottomNavigation)
    BottomNavigation bottomNavigation;

    SharedPreferences sharedPreferences;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AAAA", "Create");
        bottomNavigation.setOnMenuItemClickListener(this);

        sharedPreferences = getSharedPreferences("Pref",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        //int index = 0;
        int index = sharedPreferences.getInt("menu",0);
        if(this instanceof AllMangaActivity) {
            index = 0;
            editor.putInt("menu", index);
        }else if(this instanceof FavorisActivity)  {
            index = 1;
            editor.putInt("menu", index);
        }else if (this instanceof SearchActivity){
            index = 2;
            editor.putInt("menu", index);
        }/*else if(this instanceof OptionActivity)  {
            index = 3;
            editor.putInt("menu", index);
        }*/
        editor.apply();

        Log.i("AAAA", "index = " +index);

        bottomNavigation.setDefaultSelectedIndex(index);
    }

    @Override
    public void onMenuItemSelect(final int itemId, final int position) {
        Log.i("TAG", "onMenuItemSelect(" + itemId + ", " + position + ")");
        Log.i("TAG", "-----------------------------------");

        bottomNavigation.getBadgeProvider().remove(itemId);
        Intent intent;
        switch (itemId) {
            case R.id.item_all:
                intent = new Intent(this, AllMangaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;

            case R.id.item_favoris:
                intent = new Intent(this, FavorisActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;

            case R.id.item_search:
                intent = new Intent(this, SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;

            /*case R.id.item_option:
                intent = new Intent(this, OptionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;*/
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position) {
        Log.i("TAG", "onMenuItemReselect(" + itemId + ", " + position + ")");
    }

}
