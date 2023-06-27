package com.zehrabetuljava.spacegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OyunEkraniActivity extends AppCompatActivity {
   private  OyunGorunumu oyunGorunumu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        oyunGorunumu = new OyunGorunumu(this,point.x,point.y);

        setContentView(oyunGorunumu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        oyunGorunumu.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        oyunGorunumu.resume();
    }
}