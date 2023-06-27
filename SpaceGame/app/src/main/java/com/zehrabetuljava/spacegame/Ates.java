package com.zehrabetuljava.spacegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Ates {
    int X,Y,genislik,yukseklik;
    Bitmap ates;

    Ates(Resources resources){
        ates = BitmapFactory.decodeResource(resources,R.drawable.fireball);

        genislik = ates.getWidth();
        yukseklik=ates.getHeight();

        genislik /=15;
        yukseklik /=15;

        ates = Bitmap.createScaledBitmap(ates,genislik,yukseklik,false);

    }
    Rect getCarpmaKontrol(){
        return new Rect(X,Y,X+genislik,Y+yukseklik);
    }

}
