package com.jamobox.flashlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class FlashAlerts {

    private AlertDialog alert;
    private Activity activity;

    public FlashAlerts(Activity activity) {
        this.activity = activity;
        alert = new AlertDialog.Builder(activity).create();
    }

    public void showAlert(String title, String msg) {
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        alert.show();
    }

}
