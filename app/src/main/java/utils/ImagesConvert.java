package utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImagesConvert {
    public static String convertBase64(Bitmap map){
        if(map != null){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            map.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        return "";
    }

    public static Bitmap convertBitmap(String base){
        byte[] byteArray = Base64.decode(base, Base64.DEFAULT);
        return Bitmap.createBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
    }
}
