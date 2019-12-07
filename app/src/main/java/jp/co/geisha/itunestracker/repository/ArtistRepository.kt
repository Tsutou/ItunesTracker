package jp.co.geisha.itunestracker.repository

import retrofit2.Response
import jp.co.geisha.itunestracker.api.ItunesApi
import jp.co.geisha.itunestracker.api.entity.Artist

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
