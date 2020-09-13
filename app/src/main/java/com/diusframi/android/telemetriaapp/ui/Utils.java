package com.diusframi.android.telemetriaapp.ui;

import android.app.ProgressDialog;
import android.content.Context;

import com.diusframi.android.telemetriaapp.R;

public class Utils {

    public static ProgressDialog showDialog(Context context,String message){
        ProgressDialog dialog = ProgressDialog.show(context,
                context.getString(R.string.app_name),message, true);
        return dialog;
    }
}
