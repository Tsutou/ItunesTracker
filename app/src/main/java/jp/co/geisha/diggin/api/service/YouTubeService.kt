package jp.co.geisha.diggin.api.service

import jp.co.geisha.diggin.api.entity.YouTubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {

    @GET("/youtube/v3/search")
    suspend fun getVideoList(
            @Query("type") type: String,
            @Query("part") part: String,
            @Query("q") query: String,
            @Query("key") key: String,
            @Query("maxResults") maxResults: Int,
            @Query("videoCategoryId") videoCategoryId: Int
    ) : Response<YouTubeResponse>
}
