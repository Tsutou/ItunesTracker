package jp.co.geisha.itunestracker.service.repository

import jp.co.geisha.itunestracker.service.model.ArtistList
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import jp.co.geisha.itunestracker.service.HTTPS_API_ITUNES_URL
import jp.co.geisha.itunestracker.service.service.ItunesService

class ArtistRepository private constructor() {

    private val itunesService: ItunesService

    companion object {

        private var artistRepository: ArtistRepository? = null

        val instance: ArtistRepository
            @Synchronized get() {
                return artistRepository ?: ArtistRepository()
            }
    }

    init {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(HTTPS_API_ITUNES_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        itunesService = retrofit.create(ItunesService::class.java)
    }

    suspend fun getMusicVideoList(
            artistName: String,
            entity: String,
            limit: Int
    ): Response<ArtistList> = itunesService.getArtistList(artistName, entity, limit)
}
