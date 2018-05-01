package com.example.amconsofttest.ui.base;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpFragment;
import com.example.amconsofttest.ui.BaseView;

public abstract class BaseFragment extends MvpAppCompatFragment implements BaseView {

    @Override
    public void showProgressBar() {
        DialogUtil.displayProgressDialog(getActivity());
    }

    @Override
    public void hideProgressBar() {
        DialogUtil.hideProgressDialog();
    }

    @Override
    public void showToast(int id) {
        Toast.makeText(getActivity(), getText(id), Toast.LENGTH_LONG).show();
    }

}
