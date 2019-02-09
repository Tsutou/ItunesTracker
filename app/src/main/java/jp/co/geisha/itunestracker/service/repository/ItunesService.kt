package jp.co.geisha.itunestracker.service.repository

import jp.co.geisha.itunestracker.service.model.Artist
import jp.co.geisha.itunestracker.service.model.ArtistList

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {

    //一覧
    @GET("search")
    fun getArtistList(@Query("term") artist: String, @Query("entity") entity: String, @Query("limit") limit: Int): Call<ArtistList>

    //一覧
    @GET("search")
    fun getArtistListByGenre(
            @Query("term") keyword: String,
            @Query("entity") entity: String,
            @Query("genreId") genreId: Int,
            @Query("limit") limit: Int): Call<ArtistList>
}
