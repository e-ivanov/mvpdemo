package info.eivanov.weatherforecastr.fragments;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.Editable;
import android.widget.Button;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.nio.CharBuffer;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.activities.MainActivity;
import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.model.Main;
import info.eivanov.weatherforecastr.presenters.AddNewLocationPresenter;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;

import static org.junit.Assert.*;

/**
 * Created by killer on 9/8/17.
 */
@RunWith(AndroidJUnit4.class)
public class AddNewLocationFragmentTest {


    @Rule
    public final ActivityTestRule<MainActivity> testRule =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, false);

    private AddNewLocationFragment fragment;

    private final Instrumentation inst = InstrumentationRegistry.getInstrumentation();


    @Before
    public void init(){
        fragment = new AddNewLocationFragment();
        this.testRule.launchActivity(new Intent());
        testRule.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment).commit();

        inst.waitForIdleSync();

    }

    @Test
    public void testOnClickCancelCallsPresenterCancel() throws Exception {
        onView(withId(R.id.btnClearCurrentSelection)).perform(click());
        inst.waitForIdleSync();
        Mockito.verify(fragment.getPresenter(), Mockito.times(1)).clearCurrentSelection();
    }

    @Test
    public void testOnClickSaveCallsPresenterSave(){
        onView(withId(R.id.btnSaveNewLocation)).perform(click());
        inst.waitForIdleSync();
        Mockito.verify(fragment.getPresenter(), Mockito.times(1)).saveLocation();
    }

    @Test
    public void testShouldNotCallServiceWithLessThanThreeSymbols(){
        onView(withId(R.id.searchCityTxt)).perform(typeText("s"));
        onView(withId(R.id.searchCityTxt)).perform(typeText("o"));
        inst.waitForIdleSync();
        Mockito.verify(fragment.getPresenter(), Mockito.never()).onTextChanged(Mockito.any(CharSequence.class));
    }

}