package info.eivanov.weatherforecastr;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static org.junit.Assert.*;

import info.eivanov.weatherforecastr.activities.MainActivity;

/**
 * Created by killer on 9/8/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTestCase extends ActivityInstrumentationTestCase2 {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    public MainActivityTestCase(Class activityClass) {
        super(activityClass);
    }

//    @Test
//    public void testshowAddNewLocationScreen(){
//        onView(withId(R.id.))
//    }
}
