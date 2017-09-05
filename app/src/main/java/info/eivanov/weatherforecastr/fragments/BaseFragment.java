package info.eivanov.weatherforecastr.fragments;

import android.app.Fragment;

import info.eivanov.weatherforecastr.view.BaseView;

/**
 * Created by killer on 9/4/17.
 */

public class BaseFragment extends Fragment {

    private final ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance();
    private boolean isDialogAlreadyShown = false;
    protected void showLoadingIndicator() {
        if(!isDialogAlreadyShown){
            progressDialog.show(getActivity().getFragmentManager(), "Progress");
            isDialogAlreadyShown = true;
        }

    }

    protected void hideLoadingIndicator() {
        if(isDialogAlreadyShown){
            progressDialog.dismiss();
            isDialogAlreadyShown = false;
        }
    }
}
