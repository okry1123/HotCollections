package com.threedroid.hotcollections.util;

import android.graphics.Bitmap;

import com.threedroid.hotcollections.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 14-9-1.
 */
public class Util {
    public static void saveMyBitmap(Bitmap bmp, String filename) {
        File f = new File(MyApplication.getAppContext().getExternalCacheDir().getAbsolutePath() + filename);
        f.delete();
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
