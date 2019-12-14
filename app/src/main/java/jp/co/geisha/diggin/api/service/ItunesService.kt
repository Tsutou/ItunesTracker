package jp.co.geisha.diggin.api.service

import jp.co.geisha.diggin.api.entity.ItunesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {

    @GET("search")
    suspend fun getSampleVideoList(
            @Query("term") artist: String,
            @Query("entity") entity: String,
            @Query("limit") limit: Int): Response<ItunesData.Result>
}
