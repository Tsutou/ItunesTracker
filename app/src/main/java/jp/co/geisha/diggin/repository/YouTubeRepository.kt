package jp.co.geisha.diggin.repository

import jp.co.geisha.diggin.api.YouTubeApi
import jp.co.geisha.diggin.api.entity.YouTubeResponse
import retrofit2.Response

class YouTubeRepository private constructor() {

    companion object {
        @Volatile
        private var INSTANCE: YouTubeRepository? = null

        fun getInstance(): YouTubeRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: YouTubeRepository()
                            .also { INSTANCE = it }
                }
    }

    suspend fun getMusicVideoList(
            query: String
    ): Response<YouTubeResponse> = YouTubeApi.getService().getVideoList(
            "video",
            "snippet",
            query,
            "",
            5,
            10
    )
}