package info.eivanov.weatherforecastr.test.presenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.concurrent.Callable;

import info.eivanov.weatherforecastr.utils.CityFactory;
import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.presenters.AddNewLocationPresenter;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by killer on 9/8/17.
 */
public class AddNewLocationPresenterTest {


    @Mock
    private GetWeatherInfoRepo getWeatherInfoRepo;

    @Mock
    private Navigator navigator;

    @Mock
    private CurrentLocationsRepo currentLocationsRepo;

    @Mock
    private AddNewLocationContract.View view;

    private AddNewLocationPresenter presenter;


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
    public void initPresenter(){
        MockitoAnnotations.initMocks(this);
        presenter = new AddNewLocationPresenter(currentLocationsRepo,getWeatherInfoRepo,
                                                navigator);

        presenter.setView(view);
    }

    @Test
    public void cancelShouldCallNavigator() throws Exception {
        presenter.cancel();
        verify(navigator, times(1)).showCurrentLocationsScreen();
    }

    @Test
    public void unsubscribe() throws Exception {

    }

    @Test
    public void getCurrentSelectionReturnsNullWhenNotSet() throws Exception {
        assertNull(presenter.getCurrentSelection());
    }

    @Test
    public void getCurrentSelectionReturnsWhenIsSet() throws Exception{
        City city = CityFactory.createTestDummy();
        presenter.setCurrentSelection(city);
        assertEquals(city, presenter.getCurrentSelection());
    }

    @Test
    public void setCurrentSelection() throws Exception {
        City city = CityFactory.createTestDummy();
        presenter.setCurrentSelection(city);
        presenter.saveLocation();
        verify(view, times(1)).setCityDetails(city);
    }

    @Test
    public void saveLocationWhenLocationIsSelected() throws Exception {
        City city = CityFactory.createTestDummy();
        presenter.setCurrentSelection(city);
        presenter.saveLocation();
        verify(currentLocationsRepo, times(1)).addCity(city);
        verify(navigator, times(1)).showCurrentLocationsScreen();
    }

    @Test
    public void saveLocationWhenLocationIsNotSelected()throws Exception{
        presenter.saveLocation();
        verify(currentLocationsRepo, never()).addCity(any(City.class));
    }

    @Test
    public void clearCurrentSelection() throws Exception {
        City city = CityFactory.createTestDummy();
        presenter.setCurrentSelection(city);
        presenter.clearCurrentSelection();
        verify(view, times(1)).clearCityDetails();
        assertNull(presenter.getCurrentSelection());
    }

    @Test
    public void onTextChanged() throws Exception {
        when(getWeatherInfoRepo.searchLocations("s")).thenReturn(Observable.<City>empty());
        presenter.onTextChanged("s");
        verify(getWeatherInfoRepo, times(1)).searchLocations("s");
    }

}