package info.eivanov.weatherforecastr;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import info.eivanov.weatherforecastr.di.components.ApplicationComponent;
import info.eivanov.weatherforecastr.di.components.DaggerApplicationComponent;
import info.eivanov.weatherforecastr.di.components.PresenterComponent;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.di.modules.RepositoryModule;
import info.eivanov.weatherforecastr.timber.ReleaseTree;
import timber.log.Timber;

/**
 * Created by killer on 9/1/17.
 */

public class WeatherForecastrApp extends Application{
    private ApplicationComponent applicationComponent;

    public static WeatherForecastrApp getApp(Context ctx){
        return (WeatherForecastrApp)ctx.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }else{
            Timber.plant(new ReleaseTree());
        }
//        Fabric.with(this, new Crashlytics());
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://api.openweathermap.org/data/2.5/"))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

}
