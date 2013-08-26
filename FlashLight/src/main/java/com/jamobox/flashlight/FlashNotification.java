package com.jamobox.flashlight;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class FlashNotification {

    NotificationCompat.Builder builder;
    NotificationManager manager;

    public FlashNotification(Context context) {
        builder = new NotificationCompat.Builder(context);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showNotification() {
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("FlashLight");
        builder.setContentText("FlashLight is currently on");
        builder.setOngoing(true);

        manager.notify(FlashModes.FLASH_ON, builder.build());
    }

    public void closeNotification() {
        manager.cancel(FlashModes.FLASH_ON);
    }

}
