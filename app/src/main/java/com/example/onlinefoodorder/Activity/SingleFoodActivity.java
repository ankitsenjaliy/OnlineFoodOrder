package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.AddCartModel;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleFoodActivity extends AppCompatActivity {

    ImageView iv_food_image,iv_back_arrow;
    TextView tv_hotel_name,tv_food_name,tv_food_price,tv_food_description;
    MaterialButton btn_add_to_cart,btn_order_now;
    MKLoader five_pulse;

    Integer  Customer_Id;
    String F_Id,H_Name,F_Name,F_Price,F_Description,F_Image;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_single_food );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );
        iv_food_image = findViewById( R.id.iv_food_image );

        tv_hotel_name = findViewById( R.id.tv_hotel_name );
        tv_food_name = findViewById( R.id.tv_food_name );
        tv_food_price = findViewById( R.id.tv_food_price );
        tv_food_description = findViewById( R.id.tv_food_description );

        btn_add_to_cart = findViewById( R.id.btn_add_to_cart );
        btn_order_now = findViewById( R.id.btn_order_now );
        five_pulse = findViewById( R.id.five_pulse );

        sharedPreferences = getSharedPreferences( "mypreference", Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );

        Customer_Id = sharedPreferences.getInt( "Customer_Id", 0 );
        F_Id = getIntent().getStringExtra( "F_Id");

        H_Name = getIntent().getStringExtra( "H_Name");
        tv_hotel_name.setText( "Hotel Name :- " + H_Name );
        F_Name = getIntent().getStringExtra( "F_Name" );
        tv_food_name.setText( "Food Name :- " + F_Name );
        F_Price = getIntent().getStringExtra( "F_Price" );
        tv_food_price.setText( "Food Price :- ₹ " + F_Price );
        F_Description = getIntent().getStringExtra( "F_Description" );
        tv_food_description.setText( "Food Description :- " + F_Description );
        F_Image = getIntent().getStringExtra( "F_Image" );
        Glide.with( this )
                .load( F_Image )
                .placeholder( R.color.orange )
                .into( iv_food_image );

        btn_order_now.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SingleFoodActivity.this, AddCartActivity.class);
                intent.putExtra( "F_Id",F_Id );
                intent.putExtra( "F_Name",F_Name );
                intent.putExtra( "F_Price", F_Price );
                intent.putExtra( "F_Image", F_Image );
                startActivity( intent );
            }
        } );

        btn_add_to_cart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                five_pulse.setVisibility( View.VISIBLE );

                add_cart_data( Integer.valueOf( F_Id ),Customer_Id,H_Name,F_Name,F_Price,F_Description,F_Image);
            }
        } );

    }

    private void add_cart_data(Integer f_Id, Integer customer_Id, String h_Name, String f_Name, String f_Price, String f_Description, String f_Image){

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<AddCartModel> addCartModelCall = api.add_cart( f_Id,customer_Id,h_Name,f_Name,f_Price,f_Description,f_Image );

        addCartModelCall.enqueue( new Callback<AddCartModel>() {
            @Override
            public void onResponse(Call<AddCartModel> call, Response<AddCartModel> response) {

                if(response.body().getSuccess()){

                    five_pulse.setVisibility( View.GONE );

                    Toast.makeText( SingleFoodActivity.this, "Food Add To Cart", Toast.LENGTH_SHORT ).show();

                }else{

                    five_pulse.setVisibility( View.GONE );

                    Toast.makeText( SingleFoodActivity.this, "Not Food Add To Cart", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<AddCartModel> call, Throwable t) {

                five_pulse.setVisibility( View.GONE );

                Toast.makeText( SingleFoodActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}