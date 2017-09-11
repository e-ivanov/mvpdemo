package info.eivanov.weatherforecastr.fragments;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mockito.Mockito;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.retrofit.MapAPIService;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by killer on 9/9/17.
 */
@Module
public class NetworkMockModule{


    @Provides
    @Singleton
    public Gson provideGson(){
        return Mockito.mock(Gson.class);
    }

    @Provides
    @Singleton
    public Cache provideOkHttpCache(Application application){
        return Mockito.mock(Cache.class);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache){
        return Mockito.mock(OkHttpClient.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient){
        return Mockito.mock(Retrofit.class);
    }

    @Provides
    @Singleton
    public MapAPIService providesMapAPIService(Retrofit retrofit){
        return Mockito.mock(MapAPIService.class);
    }
}
