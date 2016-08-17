package com.xapp.jjh.xui.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.xapp.jjh.xui.R;
import com.xapp.jjh.xui.engine.DialogEngine;
import com.xapp.jjh.xui.inter.DialogListener;
import com.xapp.jjh.xui.inter.IToolsInterface;
import com.xapp.jjh.xui.lib.niftydialog.DialogInfo;
import com.xapp.jjh.xui.utils.ToastUtils;

/**
 * Created by Taurus on 16/8/14.
 */
public abstract class ToolsFragment extends FilterFragment implements IToolsInterface{

    @Override
    public <T extends View> T findView(int id){
        if(mUserView ==null)
            return null;
        return (T) mUserView.findViewById(id);
    }

    protected View inflate(int res){
        return inflate(res, R.style.AppBaseTheme);
    }

    protected View inflate(int res, int theme) {
        return LayoutInflater.from(new ContextThemeWrapper(mContext, theme)).inflate(res, null);
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showShortToast(mContext,message);
    }

    @Override
    public void showDialog(String message, DialogListener dialogListener) {
        DialogEngine.showDialog(getActivity(), DialogInfo.getDefaultDoubleButton(mContext,message),dialogListener);
    }

    @Override
    public void showDialog(DialogInfo dialogInfo, DialogListener dialogListener) {
        DialogEngine.showDialog(getActivity(),dialogInfo,dialogListener);
    }

    @Override
    public void showDialogSingleButton(String message, DialogListener dialogListener) {
        DialogEngine.showDialog(getActivity(),DialogInfo.getDefaultSingleButton(mContext,message),dialogListener);
    }

    @Override
    public void showDialogSingleButton(String message, String buttonText, DialogListener dialogListener) {
        DialogEngine.showDialog(getActivity(),DialogInfo.getDefaultSingleButton(message,buttonText),dialogListener);
    }

    @Override
    public boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    @Override
    public boolean isWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public void fullScreen() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void quitFullScreen(){
        final WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().setAttributes(attrs);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public void keepScreenOn(){
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void closeKeyCode(Context ct) {
        View view = ((Activity) ct).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManger = (InputMethodManager) ct.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
