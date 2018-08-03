package jp.co.geisha.itunestracker.service.repository;

import jp.co.geisha.itunestracker.service.model.Artist;
import jp.co.geisha.itunestracker.service.model.ArtistList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesService {
    //Retrofitインターフェース(APIリクエストを管理)
    String HTTPS_API_ITUNES_URL = "https://itunes.apple.com/";

    //一覧
    @GET("search")
    Call <ArtistList> getArtistList(@Query("term") String artist, @Query("entity") String entity, @Query("limit") int limit);

//
//    //詳細(現状使ってないい)
//    @GET("/repos/{user}/{reponame}")
//    Call<Artist> getArtistDetails(@Path("artisitName") String artist,@Path("artistId") String artistName);
}
