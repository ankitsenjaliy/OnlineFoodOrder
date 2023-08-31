package com.example.onlinefoodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.onlinefoodorder.Activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    ImageView splash1,splash2;
    Animation left,right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        splash1 = findViewById( R.id.splash1 );
        splash2 = findViewById( R.id.splash2 );

        left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.main_animation );
        right = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.sub_animation );

        splash1.setAnimation( left );
        splash2.setAnimation( right );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);

    }
}