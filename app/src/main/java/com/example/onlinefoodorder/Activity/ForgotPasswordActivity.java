package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.ForgotPasswordModel;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText et_email_id;
    MaterialButton btn_send;
    MKLoader whirlpool;

    String C_Email_Id;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forgot_password );

        et_email_id = findViewById( R.id.et_email_id );
        btn_send = findViewById( R.id.btn_send );
        whirlpool = findViewById( R.id.whirlpool );

        btn_send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                C_Email_Id = et_email_id.getText().toString();

                if(C_Email_Id.equals( "" )){

                    Toast.makeText( ForgotPasswordActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT ).show();

                }else if(!C_Email_Id.contains( "@" ) || !C_Email_Id.contains( ".com" )){

                    Toast.makeText( ForgotPasswordActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT ).show();

                }else{

                    whirlpool.setVisibility( View.VISIBLE );

                    forgot_password_data();

                }

            }
        } );
    }

    private void forgot_password_data(){

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<ForgotPasswordModel> forgotPasswordModelCall = api.forgot_password( C_Email_Id );

        forgotPasswordModelCall.enqueue( new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {

                if(response.body().getSuccess()){

                    whirlpool.setVisibility( View.GONE );

                    Toast.makeText( ForgotPasswordActivity.this, "Password Send To Email Id, Please Check Your Email Id", Toast.LENGTH_SHORT ).show();

                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity( intent );
                    finish();

                }else{

                    whirlpool.setVisibility( View.GONE );

                    Toast.makeText( ForgotPasswordActivity.this, "Not Valid Email Id", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {

                whirlpool.setVisibility( View.GONE );

                Toast.makeText( ForgotPasswordActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}