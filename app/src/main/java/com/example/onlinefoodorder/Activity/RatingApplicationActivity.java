package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.RatingApplicationModel;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingApplicationActivity extends AppCompatActivity {

    ImageView iv_back_arrow;
    RatingBar rb_rating_bar;
    EditText et_rating_description;
    MaterialButton btn_submit;

    MKLoader classic_spinner;

    String Rating_No,Rating_Description;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rating_application );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );
        rb_rating_bar = findViewById( R.id.rb_rating_bar );
        et_rating_description = findViewById( R.id.et_rating_description );
        btn_submit = findViewById( R.id.btn_submit );

        classic_spinner = findViewById( R.id.classic_spinner );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );

        btn_submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Rating_No = ""+rb_rating_bar.getRating();
                Rating_Description = et_rating_description.getText().toString();

                if(Rating_No.equals( "" )){

                    Toast.makeText( RatingApplicationActivity.this, "Please Enter Application Rating", Toast.LENGTH_SHORT ).show();

                }else if(Rating_Description.equals( "" )){

                    Toast.makeText( RatingApplicationActivity.this, "Please Enter Application Rating Description", Toast.LENGTH_SHORT ).show();

                }else{

                    classic_spinner.setVisibility( View.VISIBLE );

                    rating(Rating_No,Rating_Description);
                }

            }
        } );
    }

    private void rating(String R_No, String R_Description){

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<RatingApplicationModel> ratingApplicationModelCall = api.rating_application( R_No, R_Description );

            ratingApplicationModelCall.enqueue( new Callback<RatingApplicationModel>() {
                @Override
                public void onResponse(Call<RatingApplicationModel> call, Response<RatingApplicationModel> response) {

                    if(response.body().getSuccess()){

                        classic_spinner.setVisibility( View.GONE );

                        Toast.makeText( RatingApplicationActivity.this, "Rating Added SuccessFully", Toast.LENGTH_SHORT ).show();

                        Intent intent = new Intent(RatingApplicationActivity.this,NavigationActivity.class);
                        startActivity( intent );
                        finish();

                    }else{

                        classic_spinner.setVisibility( View.GONE );

                        Toast.makeText( RatingApplicationActivity.this, "Rating Added Failed", Toast.LENGTH_SHORT ).show();
                    }

                }

                @Override
                public void onFailure(Call<RatingApplicationModel> call, Throwable t) {

                    classic_spinner.setVisibility( View.GONE );

                    Toast.makeText( RatingApplicationActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();
                }
            } );

    }
}