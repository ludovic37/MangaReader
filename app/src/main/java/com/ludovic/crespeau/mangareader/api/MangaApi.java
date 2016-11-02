package com.ludovic.crespeau.mangareader.api;

import com.ludovic.crespeau.mangareader.model.MangaList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by crespeau on 01/11/2016.
 */

public interface MangaApi {

    //TODO:
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/manga/{mangaid}/{chapterid}")
    public Call<Void> getChapter(String siteid, String mangaid, int chapterid);


    //TODO:
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/search/genres")
    public Call<Void> getGenreList(String siteid, String mangaid, int chapterid);


    //TODO:
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/manga/{mangaid}")
    public Call<Void> getManga(String siteid, String mangaid, int chapterid);


    //TODO:
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/search/genres/{chapterid}")
    public Call<Void> getMangaByGenre(String siteid, String mangaid, int chapterid);


    /**
     * Request for all manga
     */
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net?cover=1&info=1")
    public Call<List<MangaList>> getMangaList();


    //TODO:
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/")
    public Call<Void> getSiteList(String siteid, String mangaid, int chapterid);


    //TODO:
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/search")
    public Call<Void> search(String siteid, String mangaid, int chapterid);
}
