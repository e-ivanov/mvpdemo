package info.eivanov.weatherforecastr;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by killer on 9/9/17.
 */

@Module
public class ApplicationMockModule {
    private final Application mApplication;

    public ApplicationMockModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    @Named("defaultPrefs")
    SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    @Named("dataPrefs")
    SharedPreferences provideDataPreferences(Application application){
        return application.getSharedPreferences("data", Context.MODE_PRIVATE);
    }
}
