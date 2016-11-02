package com.ludovic.crespeau.mangareader.model;

import java.util.List;

/**
 * Created by crespeau on 01/11/2016.
 */

public class Manga {
    public String name;
    public String href;
    public List<String> author;
    public List<String> artist;
    public String status;
    public int yearOfRelease;
    public List<String> genres;
    public String info;
    public String cover;
    public String lastUpdate;
    public List<Chapters> chapterses;
}
