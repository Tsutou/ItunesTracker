package jp.co.geisha.itunestracker.api.service

import jp.co.geisha.itunestracker.api.entity.Artist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {

    @GET("search")
    suspend fun getArtistList(
            @Query("term") artist: String,
            @Query("entity") entity: String,
            @Query("limit") limit: Int): Response<Artist.Result>
}
