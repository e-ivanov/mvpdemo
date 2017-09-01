package info.eivanov.weatherforecastr;

import android.app.Application;
import android.content.Context;

import info.eivanov.weatherforecastr.di.components.DaggerRepositoryComponent;
import info.eivanov.weatherforecastr.di.components.RepositoryComponent;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.timber.ReleaseTree;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by killer on 9/1/17.
 */

public class WeatherForecastrApp extends Application{
    private RepositoryComponent repositoryComponent;

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
        repositoryComponent = DaggerRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://localhost"))
                .build();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/weathericons-regular-webfont.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //....

    }

    public RepositoryComponent getRepositoryComponent(){
        return repositoryComponent;
    }
}
