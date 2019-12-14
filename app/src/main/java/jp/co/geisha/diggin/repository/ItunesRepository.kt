package jp.co.geisha.diggin.repository

import retrofit2.Response
import jp.co.geisha.diggin.api.ItunesApi
import jp.co.geisha.diggin.api.entity.ItunesData

class ItunesRepository private constructor() {

    companion object {
        @Volatile
        private var INSTANCE: ItunesRepository? = null

        fun getInstance(): ItunesRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: ItunesRepository()
                            .also { INSTANCE = it }
                }
    }

    suspend fun getMusicVideoList(
            artistName: String,
            entity: String,
            limit: Int
    ): Response<ItunesData.Result> = ItunesApi.getService().getSampleVideoList(artistName, entity, limit)
}
