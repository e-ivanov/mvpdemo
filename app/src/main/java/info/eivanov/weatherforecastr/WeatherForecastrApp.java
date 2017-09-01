package info.eivanov.weatherforecastr;

import android.app.Application;
import android.content.Context;

import info.eivanov.weatherforecastr.di.components.DaggerNetComponent;
import info.eivanov.weatherforecastr.di.components.NetComponent;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.timber.ReleaseTree;
import timber.log.Timber;

/**
 * Created by killer on 9/1/17.
 */

public class WeatherForecastrApp extends Application{
    private NetComponent netComponent;

    public static WeatherForecastrApp getApp(Context ctx){
        return (WeatherForecastrApp)ctx.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }else{
            Timber.plant(new ReleaseTree());
        }
//        Fabric.with(this, new Crashlytics());
        netComponent = DaggerNetComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://localhost"))
                .build();

    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}
