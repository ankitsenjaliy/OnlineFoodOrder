package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinefoodorder.R;

public class SettingsActivity extends AppCompatActivity {

    ImageView iv_back_arrow;
    TextView tv_share_application,tv_rate_application,tv_more_application,tv_privacy_policy,tv_about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_settings );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );

        tv_share_application = findViewById( R.id.tv_share_application );
        tv_rate_application = findViewById( R.id.tv_rate_application );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );

        tv_share_application.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType( "text/plain" );
                String app_url = "https://play.google.com/store/apps/details?id=com.example.onlinefoodorder.Activity";
                intent.putExtra( android.content.Intent.EXTRA_TEXT,app_url );
                startActivity( intent );
            }
        } );

        tv_rate_application.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingsActivity.this,RatingApplicationActivity.class);
                startActivity( intent );
            }
        } );
    }
}