package com.zehrabetuljava.spacegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OyunGorunumu extends SurfaceView implements Runnable{
    private boolean isPlaying, oyunBitti=false;
    private Thread thread;

     ArkaPlan arkaPlan1,arkaPlan2;
    // float ekranBuyukluguX, ekranBuyukluguY;
     UzayGemisi uzayGemisi;
     List<Ates> atesler ;
     Dusman [] dusmanlar;
     int ekranX, ekranY;
     Paint paint;

    public OyunGorunumu(Context context, int ekranX ,int ekranY) {
        super(context);
        this.ekranX = ekranX;
        this.ekranY = ekranY;
//        ekranBuyukluguX = 1920f / ekranX;
//        ekranBuyukluguY = 1080f/ekranY;
        uzayGemisi = new UzayGemisi(this,ekranX,ekranY,getResources());
        atesler = new ArrayList<>();
        dusmanlar = new Dusman[4];
        arkaPlan1 = new ArkaPlan(ekranX,ekranY,getResources());
        arkaPlan2 = new ArkaPlan(ekranX,ekranY,getResources());
        arkaPlan2.setY(ekranY);
        paint = new Paint();
        for (int i =0; i<4; i++){
            Dusman dusman = new Dusman(ekranX,getResources());
            dusmanlar[i]=dusman;
        }
    }

    @Override
    public void run() {
       while (isPlaying){
           update();
           draw();
           sleep();
       }
    }
    public void update(){
       arkaPlan1.setY(arkaPlan1.getY()-10);
       arkaPlan2.setY(arkaPlan2.getY()-10);
       if(arkaPlan1.getY()+arkaPlan1.arkaPLanBitmap.getHeight()<0){
           arkaPlan1.setY(ekranY);
       }
        if(arkaPlan2.getY()+arkaPlan2.arkaPLanBitmap.getHeight()<0){
            arkaPlan2.setY(ekranY);
        }
        if(uzayGemisi.sagaGit){
            uzayGemisi.X +=10;

        }
        if(uzayGemisi.solaGit){
            uzayGemisi.X -=10;

        }
        if(uzayGemisi.X<=0){
            uzayGemisi.X = 0;
        }
        if(uzayGemisi.X>=ekranX - uzayGemisi.genislik){
            uzayGemisi.X = ekranX - uzayGemisi.genislik;
        }
        List<Ates> atesList = new ArrayList<>();
        for(Ates ates : atesler){
            if(ates.Y<0)
                atesList.add(ates);
            ates.Y -=50;
            for(Dusman dusman : dusmanlar) {
                if (Rect.intersects(dusman.getCarpmaKontrol(), ates.getCarpmaKontrol())) {
                    dusman.Y = -500;
                    ates.Y = 0-ates.yukseklik;
                    dusman.vuruldu = true;
                }
            }
        }
        for (Ates ates : atesList)
            atesler.remove(ates);

        for(Dusman dusman : dusmanlar){
            dusman.Y +=dusman.hiz ;
            if(dusman.Y > ekranY){
                 if(!dusman.vuruldu){
                     oyunBitti = true;
                     return;
                 }
                dusman.X =(int)Math.floor(Math.random()*ekranX);
                dusman.hiz=(int)Math.floor(Math.random()*10)+3;
                if(dusman.X< 0){
                    dusman.X = 0;
                }
                if(dusman.X >=ekranX-dusman.genislik)
                {
                    dusman.X = ekranX - dusman.genislik;
                }
                dusman.Y=0;
                dusman.vuruldu=false;
            }
            if(Rect.intersects(dusman.getCarpmaKontrol(),uzayGemisi.getCarpmaKontrol())){
                    oyunBitti = true;
                    return;
            }
        }
    }
    public void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(arkaPlan1.arkaPLanBitmap,arkaPlan1.getX(),arkaPlan1.getY(),paint);
            canvas.drawBitmap(arkaPlan2.arkaPLanBitmap,arkaPlan2.getX(),arkaPlan2.getY(),paint);

            if(oyunBitti){
                isPlaying=false;
                canvas.drawBitmap(uzayGemisi.getKaza(),uzayGemisi.X,uzayGemisi.Y,paint);
                getHolder().unlockCanvasAndPost(canvas);
                return;
            }
            canvas.drawBitmap(uzayGemisi.getUzayGemisi(),uzayGemisi.X,uzayGemisi.Y,paint);
            for (Ates ates : atesler)
                canvas.drawBitmap(ates.ates,ates.X,ates.Y,paint);
            for(Dusman dusman : dusmanlar)
                canvas.drawBitmap(dusman.dusman,dusman.X,dusman.Y,paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    public void sleep(){
      try {
          thread.sleep(17);
      }catch(InterruptedException e){
          e.printStackTrace();
      }
    }
    public void resume(){
        isPlaying=true;
        thread = new Thread(this);
        thread.start();
    }
    public void pause(){
        isPlaying=false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getX() <= ekranX /2){
            uzayGemisi.solaGit = true;
        }
        if(event.getX() > ekranX /2){
            uzayGemisi.sagaGit = true;

        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            uzayGemisi.solaGit=false;
            uzayGemisi.sagaGit=false;
        }
        if(event.getY()<ekranY && event.getY()>= uzayGemisi.Y  && event.getX() > uzayGemisi.X && event.getX()<= uzayGemisi.X+ uzayGemisi.genislik){

            uzayGemisi.atesEt = true;
            uzayGemisi.solaGit=false;
            uzayGemisi.sagaGit=false;
        }
        return true;
    }



    public void atesOlustur(){
        Ates ates = new Ates(getResources());
        ates.X = uzayGemisi.X;
        Log.e("Yu",String.valueOf( uzayGemisi.yukseklik));
        Log.e("Y",String.valueOf(uzayGemisi.Y));
        ates.Y = ekranY - uzayGemisi.yukseklik;
        atesler.add(ates);
    }
}
