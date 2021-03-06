package com.diusframi.android.telemetriaapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.diusframi.android.telemetriaapp.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Utils {

    public static ProgressDialog showDialog(Context context,String message){
        ProgressDialog dialog = ProgressDialog.show(context,
                context.getString(R.string.app_name),message, true);
        return dialog;
    }

    public static Dialog createDialogSingleButton(Context context, String message, String buttonText){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    public static void restartApp(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        Runtime.getRuntime().exit(0);
    }
}
