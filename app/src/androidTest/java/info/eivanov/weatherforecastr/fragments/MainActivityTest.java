package info.eivanov.weatherforecastr.fragments;

import android.app.Fragment;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.activities.MainActivity;
import info.eivanov.weatherforecastr.di.scope.FragmentScope;
import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;

/**
 * Created by killer on 9/9/17.
 */

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, false);

    @Inject
    CurrentLocationsRepo currentLocationsRepo;

    @Before
    public void setUp(){
        Instrumentation inst = InstrumentationRegistry.getInstrumentation();
        WeatherForecastrApp app = (WeatherForecastrApp)inst.getTargetContext().getApplicationContext();
        ApplicationMockComponent component = (ApplicationMockComponent)app.getApplicationComponent();
        component.inject(this);
    }

    @Test
    public void testShowsAddNewFragmentWhenNoLocactionIsSaved(){
        Mockito.when(currentLocationsRepo.getLocations()).thenReturn(new ArrayList<City>());
        rule.launchActivity(null);
        Fragment f = rule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(f);
        assertTrue(f instanceof AddNewLocationFragment);
    }

    @Test
    public void testDoesNotShowLocationsListWhenThereANoSavedLocations(){
        Mockito.when(currentLocationsRepo.getLocations()).thenReturn(new ArrayList<City>());
        rule.launchActivity(null);

        Fragment f = rule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(f);
        assertFalse(f instanceof LocationsListFragment);
    }

    @Test
    public void testShowsLocationListWhenLocactionIsSaved(){
        List<City> mockList = Mockito.mock(ArrayList.class);
        Mockito.when(mockList.size()).thenReturn(1);
        Mockito.when(currentLocationsRepo.getLocations()).thenReturn(mockList);
        rule.launchActivity(null);
        Fragment f = rule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(f);
        assertTrue(f instanceof LocationsListFragment);
    }

    @Test
    public void testDoesNotShowAddNewLocationFragmentWhenLocationsIsSaved(){
        List<City> mockList = Mockito.mock(ArrayList.class);
        Mockito.when(mockList.size()).thenReturn(1);
        Mockito.when(currentLocationsRepo.getLocations()).thenReturn(mockList);
        rule.launchActivity(null);
        Fragment f = rule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertNotNull(f);
        assertFalse(f instanceof AddNewLocationFragment);
    }

}
