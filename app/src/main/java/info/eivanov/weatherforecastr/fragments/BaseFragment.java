package info.eivanov.weatherforecastr.fragments;

import android.app.Fragment;


/**
 * Created by killer on 9/4/17.
 */

public class BaseFragment extends Fragment {

    private final ProgressDialogFragment progressDialog = ProgressDialogFragment.newInstance();;
    private boolean isDialogAlreadyShown;


    protected void showLoadingIndicator() {
        if (getActivity().getFragmentManager().findFragmentByTag("Progress") == null) {
            progressDialog.show(getActivity().getFragmentManager(), "Progress");
        }

    }

    protected void hideLoadingIndicator() {

        if (getActivity().getFragmentManager().findFragmentByTag("Progress") != null) {
            if (progressDialog != null && progressDialog.isAdded()) {
                progressDialog.dismiss();
            }
        }
    }

}
