package com.ludovic.crespeau.mangareader.view.search;

import com.ludovic.crespeau.mangareader.model.Genre;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.model.Search;

import java.util.List;

/**
 * Created by crespeau on 05/11/2016.
 */

public interface SearchView {

    void updateGenre(List<Genre> listGenre);
    void resultSearch(List<MangaList> searchList);
}
