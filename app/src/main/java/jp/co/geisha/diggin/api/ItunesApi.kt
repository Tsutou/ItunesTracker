package jp.co.geisha.diggin.api

import com.squareup.moshi.Moshi
import jp.co.geisha.diggin.API_ITUNES_BASE_URL
import jp.co.geisha.diggin.api.service.ItunesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ItunesApi {
     companion object {
         @Volatile
         private var INSTANCE: ItunesService? = null

         private val interceptor = HttpLoggingInterceptor().apply {
             level = HttpLoggingInterceptor.Level.BODY
         }

         private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                 .addInterceptor(interceptor)
                 .connectTimeout(100, TimeUnit.SECONDS)
                 .readTimeout(100, TimeUnit.SECONDS)
                 .build()

         private val moshiConverterFactory =
                 MoshiConverterFactory
                         .create(Moshi.Builder().build())

         private fun createRetrofit(): Retrofit {
             return Retrofit.Builder()
                     .baseUrl(API_ITUNES_BASE_URL)
                     .client(okHttpClient)
                     .addConverterFactory(moshiConverterFactory)
                     .build()
         }

         fun getService(): ItunesService =
                 INSTANCE ?: synchronized(this) {
                     INSTANCE ?: createRetrofit().create(ItunesService::class.java)
                             .also { INSTANCE = it }
                 }
     }
}