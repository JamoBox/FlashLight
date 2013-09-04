package com.jamobox.flashlight;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    public static int flashMode;
    private FlashUtils flashUtils;
    private ImageButton toggle;
    private FlashNotification notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FlashAlerts alerts;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (ImageButton) findViewById(R.id.flashButton);
        notification = new FlashNotification(this);
        alerts = new FlashAlerts(this);
        flashUtils = new FlashUtils();

        // Detect if an LED flash is available on the device
        try {
            if (!(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))) {
                alerts.showAlert("No camera flash", "FlashLight could not detect an LED flash on your device. Sorry!");
            }
        } catch (NullPointerException e) {
            Log.e("Could not get system features", e.getMessage());
        }

        flashMode = FlashConstants.FLASH_OFF;
        flashUtils.getCamera();

        toggle.setOnClickListener(new View.OnClickListener() {

            @Override
            // Called on toggle button press
            public void onClick(View view) {
                if (flashMode == FlashConstants.FLASH_OFF) {
                    setFlashState(FlashConstants.FLASH_ON);
                } else {
                    setFlashState(FlashConstants.FLASH_OFF);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    // Run all code needed to make a smooth flash toggle
    public void setFlashState(int state) {
        if (state == FlashConstants.FLASH_ON) {
            flashUtils.flashOn();
            flashMode = FlashConstants.FLASH_ON;
            toggle.setImageResource(R.drawable.ic_toggle_button_on);
            notification.showNotification();
        } else {
            flashUtils.flashOff();
            flashMode = FlashConstants.FLASH_OFF;
            toggle.setImageResource(R.drawable.ic_toggle_button_off);
            notification.closeNotification();
        }
    }

}
