/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.yinhuidata.mytest.dialog;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by v_liwei10 on 2018/7/20.
 */

public class BaseDialogFragment extends DialogFragment {
    public static final String TAG = "BaseDialogFragment";

    @Override
    public void dismiss() {
        //        super.dismiss();
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            Log.e(TAG, "dismiss: " + e);
        }

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            if (!this.isAdded()) {
                ft.add(this, tag);
            }
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(TAG, "show: " + e);

        }
        //        mDismissed = false;
        //        mShownByMe = true;
        //        FragmentTransaction ft = manager.beginTransaction();
        //        if(!this.isAdded()){
        //            ft.add(this, tag);
        //        }
        //        ft.commitAllowingStateLoss();
    }

}
