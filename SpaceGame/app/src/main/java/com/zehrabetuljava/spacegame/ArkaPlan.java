package com.zehrabetuljava.spacegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ResourceBundle;

public class ArkaPlan {
   private int X=0,Y=0;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    Bitmap arkaPLanBitmap;

    public ArkaPlan(int ekranX, int ekranY, Resources resources) {
      arkaPLanBitmap = BitmapFactory.decodeResource(resources,R.drawable.backgroundkara);
      arkaPLanBitmap = Bitmap.createScaledBitmap(arkaPLanBitmap,ekranX,ekranY,false);
    }
}
