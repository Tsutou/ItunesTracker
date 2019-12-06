package jp.co.geisha.itunestracker.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log

import jp.co.geisha.itunestracker.service.model.Artist
import jp.co.geisha.itunestracker.service.model.ArtistList
import com.google.gson.JsonElement
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import jp.co.geisha.itunestracker.service.HTTPS_API_ITUNES_URL

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

    fun getArtistList(artistName: String, entity: String, limit: Int): LiveData<ArtistList> {
        val data = MutableLiveData<ArtistList>()

        itunesService.getArtistList(artistName, entity, limit).enqueue(object : Callback<ArtistList> {
            override fun onResponse(call: Call<ArtistList>, response: Response<ArtistList>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<ArtistList>, t: Throwable) {
                data.postValue(null)
            }
        })

        return data
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}
