package jp.co.geisha.diggin.repository

import retrofit2.Response
import jp.co.geisha.diggin.api.ItunesApi
import jp.co.geisha.diggin.api.entity.Artist

class ArtistRepository private constructor() {

    companion object {
        @Volatile
        private var INSTANCE: ArtistRepository? = null

        fun getInstance(): ArtistRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: ArtistRepository()
                            .also { INSTANCE = it }
                }
    }

    suspend fun getMusicVideoList(
            artistName: String,
            entity: String,
            limit: Int
    ): Response<Artist.Result> = ItunesApi.getService().getArtistList(artistName, entity, limit)
}
