package com.ludovic.crespeau.mangareader.interactor;

import com.ludovic.crespeau.mangareader.view.search.SearchView;

import java.util.List;

/**
 * Created by crespeau on 05/11/2016.
 */

public interface SearchInteractor {

    void manga(SearchView searchView);
    void search(SearchView searchView, String manga, List<String> genre);
}
