package com.zehrabetuljava.spacegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Dusman {

    public boolean vuruldu = true;
    int X,Y,genislik,yukseklik,hiz=2;
    Bitmap dusman;

   Dusman (int ekranX,Resources resources){

        dusman = BitmapFactory.decodeResource(resources,R.drawable.enemy_two);

        genislik = dusman.getWidth();
        yukseklik=dusman.getHeight();

        genislik /=7;
        yukseklik /=7;

        dusman = Bitmap.createScaledBitmap(dusman,genislik,yukseklik,false);
        X=(int)Math.floor(Math.random()*ekranX);
       if(X< 0){
           X = 0;
       }
       if(X >=ekranX-genislik)
       {
          X = ekranX - genislik;
       }
        Y=-yukseklik;

    }
    Rect getCarpmaKontrol(){
        return new Rect(X,Y,X+genislik,Y+yukseklik);
    }

}
