package com.example.flappyw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileNotFoundException;

import static com.example.flappyw.Constants.LiveSong;

public class StartGame extends Activity {
    GameView gameView;

    static public Bitmap Tube;
    static public Bitmap Charackter;
    static public Bitmap Background;

    RelativeLayout game;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(String.valueOf(LiveSong));
            mediaPlayer.setVolume(50,50);
            mediaPlayer.prepare();
            mediaPlayer.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        checkIntents();

        gameView = new GameView(this, null);
        setContentView(R.layout.game_view);

        game = (RelativeLayout) findViewById(R.id.gameviewLayout);
        setBackgroundPic();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backmain = new Intent(StartGame.this,MainActivity.class);
        startActivity(backmain);
        finish();
    }

    public static Bitmap returnTube(){
        return Tube;
    }

    public static Bitmap returnCharackter(){
        return Charackter;
    }

    public void setBackgroundPic(){
        BitmapDrawable ob = new BitmapDrawable(getResources(), Background);
        try {
            game.setBackground(ob);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("OK");
            game.setBackgroundResource(R.drawable.background_final);
        }
    }

    public void checkIntents(){
        if(getIntent() != null){
            try {
                Tube = BitmapFactory.decodeStream(this.openFileInput("Tube"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error Tube main");
            }
        }
        if(getIntent() != null){
            try {
                Charackter = BitmapFactory.decodeStream(this.openFileInput("Charackter"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error Charackter main");
            }
        }
        if(getIntent() != null){

            try {
                Background = BitmapFactory.decodeStream(this.openFileInput("Background"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Keine Background");
            }
        }
        if(Constants.CheckCodeTube==1){
            Tube=Constants.DefaultTube;
        }
        if(Constants.CheckCodeCharackter==1){
            Charackter=Constants.DefaultCharackter;
        }
        if(Constants.CheckCodeBackground==1){
            Background=Constants.DefaultBackground;
        }
    }

}

