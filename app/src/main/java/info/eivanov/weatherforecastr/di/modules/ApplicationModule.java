package info.eivanov.weatherforecastr.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by killer on 8/30/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    protected Application providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    @Named("defaultPrefs")
    protected SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    @Named("dataPrefs")
    protected SharedPreferences provideDataPreferences(Application application){
        return application.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

}
