package com.jamobox.flashlight;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class FlashUtils {

    private Camera cam;
    private Parameters params;

    public void getCamera() {
        if (cam == null) {
            try {
                cam = Camera.open();
                params = cam.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error", e.getMessage());
            }
        }
    }


    public void flashOn() {
        if(!(cam == null || params == null)) {
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            cam.setParameters(params);
            cam.startPreview();
        }
    }


    public void flashOff() {
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            cam.setParameters(params);
            cam.stopPreview();
    }

}
