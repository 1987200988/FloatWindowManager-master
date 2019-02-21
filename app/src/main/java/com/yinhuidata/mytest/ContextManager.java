package com.yinhuidata.mytest;

import android.content.Context;

/**
 * Created by v_liwei10 on 2018/8/30.
 */

public class ContextManager {
    private static Context mContext;

    public ContextManager() {
    }

    public static Context getContext() {
        if(mContext == null) {
            throw new RuntimeException("ContextManager Not Init Yet!");
        } else {
            return mContext;
        }
    }

    public static void setContext(Context context) {
        mContext = context;
    }
}
