package info.eivanov.weatherforecastr.timber;

import android.util.Log;


import timber.log.Timber;

/**
 * Created by killer on 8/30/17.
 */

public class ReleaseTree extends Timber.Tree{


    @Override
    protected boolean isLoggable(String tag, int priority) {
        if(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO){
            //We should not log these levels in production build
            return false;
        }
        //Log WARN, ERROR and WTF
        return true;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if(isLoggable(tag, priority)){
            if(priority == Log.ERROR || priority == Log.WARN){
                Throwable throwable = t != null ? t : new Exception(message);
//                Crashlytics.logException(throwable);
            }
        }
    }
}
