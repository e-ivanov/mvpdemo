package info.eivanov.weatherforecastr.fragments;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.mockito.Mockito;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;

/**
 * Created by killer on 9/10/17.
 */

@Module
public class ApplicationMockModule{

    @Provides
    @Singleton
    protected Application providesApplication(){
        return Mockito.mock(Application.class);
    }

    @Provides
    @Singleton
    @Named("defaultPrefs")
    protected SharedPreferences providesSharedPreferences(Application application){
        return Mockito.mock(SharedPreferences.class);
    }

    @Provides
    @Singleton
    @Named("dataPrefs")
    protected SharedPreferences provideDataPreferences(){
        return Mockito.mock(SharedPreferences.class);
    }
}
