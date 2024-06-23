package utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {
    public static final int REQUEST_ACCESS_CAMERA = 101;
    public static final int REQUEST_TAKE_PHOTO = 102;
    public static final int REQUEST_CALL_PHONE = 103;

    public static void checkCameraPermission(Context context) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.CAMERA}, REQUEST_ACCESS_CAMERA);
        } else {
            takePhoto(context);
        }
    }

    public static void takePhoto(Context context){
        Intent photoActivity = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(photoActivity.resolveActivity(context.getPackageManager()) != null){
            ((Activity) context).startActivityForResult(photoActivity, REQUEST_TAKE_PHOTO);
        }
    }

    public static void checkCallPermission(Context context, String phonenumber){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        }else{
            initCall(context, phonenumber);
        }
    }

    public static void initCall(Context context, String phonenumber){
        Intent call = new Intent(Intent.ACTION_CALL);
        if(call.resolveActivity(context.getPackageManager()) == null){
            call.setData(Uri.parse("tel:"+phonenumber));
            ((Activity) context).startActivity(call);
        }
    }
}
