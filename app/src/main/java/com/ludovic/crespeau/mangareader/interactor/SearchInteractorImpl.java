package com.ludovic.crespeau.mangareader.interactor;

import android.content.Context;
import android.util.Log;

import com.ludovic.crespeau.mangareader.api.MangaApi;
import com.ludovic.crespeau.mangareader.model.Genre;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.model.Search;
import com.ludovic.crespeau.mangareader.view.search.SearchView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by crespeau on 05/11/2016.
 */

public class SearchInteractorImpl implements SearchInteractor {

    MangaApi mangaApi;
    private Context appContext;

    private String TAG = "MANGA";

    @Inject
    public SearchInteractorImpl(MangaApi mangaApi, Context appContext)  {
        this.mangaApi = mangaApi;
        this.appContext = appContext;
    }

    @Override
    public void manga(final SearchView searchView) {
        mangaApi.getGenreList().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                List<Genre> genreList = response.body();
                searchView.updateGenre(genreList);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }

    @Override
    public void search(final SearchView searchView, String manga, List<String> genre) {

        List<String> g = null;
        String q = null;

        if (manga.compareTo("")!=0)
            q = manga;
        if (genre.size() > 0)
            g = genre;

        mangaApi.search(g,q).enqueue(new Callback<List<MangaList>>() {
            @Override
            public void onResponse(Call<List<MangaList>> call, Response<List<MangaList>> response) {
                Log.d("SEARCH","GOOD");
                List<MangaList> mangaLists = response.body();
                searchView.resultSearch(mangaLists);

            }

            @Override
            public void onFailure(Call<List<MangaList>> call, Throwable t) {
                Log.d("SEARCH","FAIL");
                Log.d("SEARCH",t.toString());
            }
        });
    }
}
