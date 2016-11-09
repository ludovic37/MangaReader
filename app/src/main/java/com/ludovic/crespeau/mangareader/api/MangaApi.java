package com.ludovic.crespeau.mangareader.api;

import android.support.annotation.Nullable;

import com.ludovic.crespeau.mangareader.model.Genre;
import com.ludovic.crespeau.mangareader.model.Manga;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.ludovic.crespeau.mangareader.model.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


    /**
     * Request for list genre of manga
     */
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/search/genres")
    public Call<List<Genre>> getGenreList();


    /**
     * Request for one manga
     */
    @Headers({
            "X-Mashape-Key: SU205o60tymshQSUrnRFZN6HZvbfp1182lQjsnjdZ6UQvXfRew",
            "Accept: text/plain"
    })
    @GET("/mangareader.net/manga/{mangaid}")
    public Call<Manga> getManga(@Path("mangaid") String mangaid);


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
    @GET("/mangareader.net/search?cover=1&info=1")
    public Call<List<MangaList>> search(@Nullable @Query("g") List<String> g, @Nullable @Query("q") String q);
    //g -> genre
    //q -> mangaid
}
