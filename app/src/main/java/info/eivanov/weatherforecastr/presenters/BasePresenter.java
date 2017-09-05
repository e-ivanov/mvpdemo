package info.eivanov.weatherforecastr.presenters;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by killer on 9/5/17.
 */

public class BasePresenter {

    protected final CompositeDisposable disposable = new CompositeDisposable();
}
