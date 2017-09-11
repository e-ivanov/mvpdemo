package info.eivanov.weatherforecastr;

import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.di.components.ApplicationComponent;
import info.eivanov.weatherforecastr.di.components.PresenterComponent;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.di.modules.PresenterModule;
import info.eivanov.weatherforecastr.di.modules.RepositoryModule;
import info.eivanov.weatherforecastr.fragments.*;
import info.eivanov.weatherforecastr.fragments.ApplicationMockModule;

/**
 * Created by killer on 9/9/17.
 */

public class TestApplication extends WeatherForecastrApp{


    private ApplicationMockComponent mockComponent;

    @Override
    protected ApplicationComponent buildAppComponent() {
        mockComponent = DaggerApplicationMockComponent.builder()
                .applicationMockModule(new info.eivanov.weatherforecastr.ApplicationMockModule(this))
                .networkMockModule(new NetworkMockModule())
                .repositoryMockModule(new RepositoryMockModule())
                .build();

        return mockComponent;
    }



    @Override
    public ApplicationComponent getApplicationComponent() {
        return mockComponent;
    }



    @Override
    public PresenterComponent getPresenterComponent(Navigator navigator) {
        return DaggerPrenterMockComponent.builder()
                .applicationMockComponent(mockComponent)
                .presenterMockModule(new PresenterMockModule())
                .build();
    }
}
