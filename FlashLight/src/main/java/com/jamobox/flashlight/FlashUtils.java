package com.jamobox.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class FlashUtils {

    private Camera cam;
    private Parameters params;

    // Get the device camera and load the parameters
    public void prepareCamera() {
        if (cam == null) {
            try {
                cam = Camera.open();
                params = cam.getParameters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Camera getCamera() {
        return cam;
    }

    /* TODO: Make this cleaner and not dependant on MainActivity */
    public void releaseCamera() {
        if (MainActivity.flashMode == FlashConstants.FLASH_ON) {
            flashOff();
        }
        cam.release();
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
        try {
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            cam.setParameters(params);
            cam.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
