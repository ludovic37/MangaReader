package com.ludovic.crespeau.mangareader.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by crespeau on 08/11/2016.
 */

public class Search {

    public String mangaId;
    public String name;
    @Nullable public String info;
    @Nullable public String cover;
    public List<String> genres;

    //public MangaList mangaList;
    //public Genre genreList;
}
