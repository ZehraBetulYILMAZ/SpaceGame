package com.zehrabetuljava.spacegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class UzayGemisi {

    OyunGorunumu oyunGorunumu;
    boolean sagaGit=false, solaGit=false,atesEt=false;
    int X,Y,genislik,yukseklik,kanathareketi=0;
    Bitmap uzayGemisi1,uzayGemisi2,duman;

     UzayGemisi(OyunGorunumu oyunGorunumu,int ekranX,int ekranY, Resources resources){
         this.oyunGorunumu = oyunGorunumu;
         uzayGemisi1 = BitmapFactory.decodeResource(resources,R.drawable.ship_one);
         uzayGemisi2 =  BitmapFactory.decodeResource(resources,R.drawable.ship_two);
         duman = BitmapFactory.decodeResource(resources,R.drawable.smoke);
         genislik = uzayGemisi1.getWidth();
         yukseklik=uzayGemisi1.getHeight();
         duman = Bitmap.createScaledBitmap(duman,genislik/3,yukseklik/3,false);
         genislik /=7;
         yukseklik /=7;

         uzayGemisi1 = Bitmap.createScaledBitmap(uzayGemisi1,genislik,yukseklik,false);
         uzayGemisi2 = Bitmap.createScaledBitmap(uzayGemisi2,genislik,yukseklik,false);

         Y=ekranY-yukseklik;
         X= (int)ekranX/2;
     }

     Bitmap getUzayGemisi(){
         if(atesEt){
             oyunGorunumu.atesOlustur();
             atesEt = false;
         }
         return uzayGemisi1;
     }
    Rect getCarpmaKontrol(){
         return new Rect(X,Y,X+genislik,Y+yukseklik);
    }
    Bitmap getKaza(){
         return duman;
    }
}
