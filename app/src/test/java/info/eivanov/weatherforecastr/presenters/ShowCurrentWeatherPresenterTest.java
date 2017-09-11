package info.eivanov.weatherforecastr.presenters;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.model.Weather;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by killer on 9/8/17.
 */
public class ShowCurrentWeatherPresenterTest {


    @Mock
    private GetWeatherInfoRepo getWeatherInfoRepo;

    @Mock
    private ShowCurrentWeatherContract.View view;

    @Mock
    private Navigator navigator;

    private ShowCurrentWeatherPresenter presenter;


    @BeforeClass
    public static void initCustomRxScheduler(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        presenter = new ShowCurrentWeatherPresenter(getWeatherInfoRepo, navigator);
        presenter.setView(view);
    }

    @Test
    public void testLoadForecastCallGetObservableWithRightParams() throws Exception {
        long cityId = 123456l;
        when(getWeatherInfoRepo.getWeatherForecastForCity(cityId))
                .thenReturn(Observable.<WeatherForecastResponse>empty());
        presenter.loadForecast(cityId);
        verify(getWeatherInfoRepo, times(1)).getWeatherForecastForCity(cityId);

    }

    @Test
    public void testGetObservableCallRepo(){

    }

    @Test
    public void unsubscribe() throws Exception {

    }

}