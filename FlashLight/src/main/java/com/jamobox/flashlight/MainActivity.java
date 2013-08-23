package com.jamobox.flashlight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    public static boolean flashMode;
    private FlashUtils flashUtils;
    private ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Detect if an LED flash is available on the device
        try {
            if (!(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))) {
                AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();

                alert.setTitle("No Flash Device!");
                alert.setMessage("Your device does not have an LED camera flash!");
                alert.setButton(AlertDialog.BUTTON_POSITIVE, "Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.show();
                return;
            }
        } catch (NullPointerException e) {
            Log.e("Could not get system features", e.getMessage());
        }

        toggle = (ToggleButton) findViewById(R.id.flashButton);
        flashMode = FlashModes.FLASH_OFF;
        flashUtils = new FlashUtils();

        flashUtils.getCamera();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Called on toggle button press
    public void toggleFlash(View view) {
        if (toggle.isChecked()) {
            setFlashState(FlashModes.FLASH_ON);
        } else {
            setFlashState(FlashModes.FLASH_OFF);
        }
    }

    // Run all code needed to make a smooth flash toggle
    public void setFlashState(boolean state) {
        if (state == FlashModes.FLASH_ON) {
            flashUtils.flashOn();
            flashMode = FlashModes.FLASH_ON;
        } else {
            flashUtils.flashOff();
            flashMode = FlashModes.FLASH_OFF;
        }
    }

}
