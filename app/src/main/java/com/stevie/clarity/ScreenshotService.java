package com.stevie.clarity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Date;
import java.util.UUID;

public class ScreenshotService extends Service {

    private static final String TAG = "MainActivity";
    String mPath;
    Bitmap bitmap;

    String[] addresses;
    Uri screenshotUri;

    public class ScreenshotBinder extends Binder {
        ScreenshotService getService() {
            return ScreenshotService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new ScreenshotBinder();

    public void takeScreenshot(Activity activity) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View view = activity.getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            String location = MediaStore.Images.Media.insertImage(
                    getApplicationContext().getContentResolver(), bitmap, mPath,
                    "Screenshot");
            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    public void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        //TODO: change this to the new location wherever I put the screenshot
        screenshotUri = Uri.fromFile(imageFile);
        intent.setDataAndType(screenshotUri, "image/*");
        startActivity(intent);
    }

    public void email(){
        addresses = new String[] {"outsidetheglass93@gmail.com", "smt11a@acu.edu"};
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "testing email send.");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("Screenshot: "));
        startActivity(emailIntent);
    }

}
