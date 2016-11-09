package com.ludovic.crespeau.mangareader.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.ludovic.crespeau.mangareader.Constants;
import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.component.AppComponent;
import com.ludovic.crespeau.mangareader.component.DaggerSearchComponent;
import com.ludovic.crespeau.mangareader.interactor.SearchInteractor;
import com.ludovic.crespeau.mangareader.model.Genre;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.view.resultActivity.ResultatActivity;
import com.ludovic.crespeau.mangareader.view.common.BottomMenuActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by crespeau on 05/11/2016.
 */

public class SearchActivity extends BottomMenuActivity implements SearchView {

    @Bind(R.id.editTextMangaSearch)
    EditText mEditTextMangaSearch;

    @Bind(R.id.recyclerViewGenres)
    ListView mListViewGenres;

    @Inject
    SearchInteractor searchInteractor;

    SearchView me = this;


    public static List<Genre> mListInterest;

    @OnClick(R.id.buttonSearch)
    public void search(){

        String titleMangaSearch = mEditTextMangaSearch.getText().toString().trim();
        List<String> genreList = new ArrayList<>();

        for (int i=0;i<mListInterest.size();i++){
            if (mListInterest.get(i).checked)
                genreList.add(mListInterest.get(i).genreId);
        }

        searchInteractor.search(me,titleMangaSearch,genreList);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchInteractor.manga(me);

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerSearchComponent.builder()
                .appComponent(appComponent)
                .searchModule(new SearchModule())
                .build()
                .inject(this);
    }

    @Override
    public void updateGenre(List<Genre> listGenre) {

        mListInterest = listGenre;

        final CheckBoxAdapter checkBoxAdapter =new CheckBoxAdapter(this,listGenre);

        mListViewGenres.setAdapter(checkBoxAdapter);
        mListViewGenres.setItemsCanFocus(false);
    }

    @Override
    public void resultSearch(List<MangaList> searchList) {
        Gson gson = new Gson();
        String jsonSearch = gson.toJson(searchList);

        Intent intent = new Intent(this, ResultatActivity.class);
        intent.putExtra(Constants.KEY_SEARCH,jsonSearch);
        startActivity(intent);
    }
}
