package com.ludovic.crespeau.mangareader.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by crespeau on 01/11/2016.
 */

public class MangaList {
    public String mangaId;
    public String name;
    @Nullable
    public String info;
    @Nullable
    public String cover;
    @Nullable
    public List<String> genres;
}
