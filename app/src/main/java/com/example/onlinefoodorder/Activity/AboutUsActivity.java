package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.onlinefoodorder.R;

public class AboutUsActivity extends AppCompatActivity {

    ImageView iv_back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about_us );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );
    }
}