package jp.co.geisha.itunestracker.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import jp.co.geisha.itunestracker.service.model.Artist;
import jp.co.geisha.itunestracker.service.model.ArtistList;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistRepository {

    private ItunesService itunesService;

    private static ArtistRepository artistRepository;

    //Retrofitクライアントのコンストラクタ//
    private ArtistRepository() {

        //ログ用
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ItunesService.HTTPS_API_ITUNES_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        itunesService = retrofit.create(ItunesService.class);

    }

    //インスタンス生成
    public synchronized static ArtistRepository getInstance() {
        if (artistRepository == null) {
            artistRepository = new ArtistRepository();
        }
        return artistRepository;
    }

    public LiveData<ArtistList> getArtistList(String artistName, String entity, int limit) {
        //LiveData
        final MutableLiveData<ArtistList> data = new MutableLiveData<>();

        itunesService.getArtistList(artistName, entity, limit).enqueue(new Callback<ArtistList>() {
            @Override
            public void onResponse(Call<ArtistList> call, Response<ArtistList> response) {

                Log.d("出てる？？？",response.body().toString());
                data.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ArtistList> call, Throwable t) {
                //TODO: null代入良くない + エラー処理
                Log.d("エラーだよおおおおお",t.toString());
                data.setValue(null);
            }
        });

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
