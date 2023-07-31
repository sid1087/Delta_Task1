package com.example.wordjumble;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class startPage extends AppCompatActivity {

    Boolean nightMODE;
    Switch switcher;
    SharedPreferences.Editor editor;
    Button buttonc1,buttonc2,settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        buttonc1 = findViewById(R.id.buttonc1);
        buttonc2 = findViewById(R.id.buttonc2);
        settingsBtn = findViewById(R.id.settingsBtn);

        MediaPlayer music = MediaPlayer.create(startPage.this, R.raw.gamemusicsfx);
        music.start();

        if (buttonc2 != null) {
            buttonc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(startPage.this, MainActivity.class);
                    startActivity(intent);                       }
            });
        }

        if (buttonc1 != null) {
            buttonc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(startPage.this, speedrun.class);
                    startActivity(intent);                          }
            });
        }

        /*buttonc1.setOnClickListener(v -> {
                 });
        buttonc2.setOnClickListener(v -> {
                    });*/


        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsMenu();
            }
        });
    }


    private void NightMode(){

        SharedPreferences sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night",false);

        if(nightMODE){
            switcher.setChecked(true);
        }

        switcher.setOnClickListener(v -> {

            if(nightMODE){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night",false);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night",true);
            }
            editor.apply();
        });
    }

   private void settingsMenu() {
       final Dialog dialog = new Dialog(startPage.this);
       dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       dialog.setCancelable(false);
       dialog.setContentView(R.layout.settings);

       switcher = dialog.findViewById(R.id.switcher);
       ImageView redCross = dialog.findViewById(R.id.crossBtn);

       NightMode();

       redCross.setOnClickListener(v -> dialog.dismiss());

       dialog.show();
   }
}
