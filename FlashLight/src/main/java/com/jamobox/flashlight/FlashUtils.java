package com.jamobox.flashlight;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class FlashUtils {

    private Camera cam;
    private Parameters params;

    // Get the device camera and load the parameters
    public void getCamera() {
        if (cam == null) {
            try {
                cam = Camera.open();
                params = cam.getParameters();
            } catch (Exception e) {
                Log.e("Camera Error", e.getMessage());
            }
        }
    }

    // Turn the camera flash on
    public void flashOn() {
        if(!(cam == null || params == null)) {
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            cam.setParameters(params);
            cam.startPreview();
        }
    }

    // Turn the camera flash off
    public void flashOff() {
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            cam.setParameters(params);
            cam.stopPreview();
    }

}
