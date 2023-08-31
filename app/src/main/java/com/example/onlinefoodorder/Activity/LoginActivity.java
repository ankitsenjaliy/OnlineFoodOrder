package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.LoginModel;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText et_phone_no,et_password;
    CheckBox cb_rememberme;
    MaterialButton btn_login,btn_cancel;
    TextView tv_new_registration, tv_forgot_password;

    MKLoader classic_spinner;

   SharedPreferences sharedPreferences;
   SharedPreferences.Editor editor;

    Integer C_Id;
    String C_Phone_No,C_Password;
    Boolean cid;

    boolean password_visible;
    APIInterface api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        et_phone_no = findViewById( R.id.et_phone_no );
        et_password = findViewById( R.id.et_password );
        cb_rememberme = findViewById( R.id.cb_rememberme );
        btn_login = findViewById( R.id.btn_login );
        btn_cancel = findViewById( R.id.btn_cancel );
        tv_new_registration = findViewById( R.id.tv_new_registration );
        tv_forgot_password = findViewById( R.id.tv_forgot_password );

        classic_spinner = findViewById( R.id.classic_spinner );

        sharedPreferences = getSharedPreferences( "mypreference", Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();

        C_Id = sharedPreferences.getInt( "Customer_Id",0 );

        cid = sharedPreferences.getBoolean( "id",false );

        if(cid){

            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(intent);
            finish();

        }
        et_password.setOnTouchListener( new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent){

                final int Right = 2;

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){

                    if(motionEvent.getRawX() >= et_password.getRight() - et_password.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = et_password.getSelectionEnd();

                        if(password_visible){
//                            set drawable image here
                            et_password.setCompoundDrawablesRelativeWithIntrinsicBounds( 0,0,R.drawable.eye_visibility_off,0 );

//                            for hide password
                            et_password.setTransformationMethod( PasswordTransformationMethod.getInstance() );
                            password_visible = false;

                        }else{
//                            set drawable image here
                            et_password.setCompoundDrawablesRelativeWithIntrinsicBounds( 0,0,R.drawable.eye_visibility_on,0 );

//                            for show password
                            et_password.setTransformationMethod( HideReturnsTransformationMethod.getInstance() );
                            password_visible = true;
                        }

                        et_password.setSelection( selection );
                        return  true;
                    }

                }

                return false;
            }
        } );

        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                C_Phone_No = et_phone_no.getText().toString();
                C_Password = et_password.getText().toString();

                if(C_Phone_No.equals( "" )){

                    Toast.makeText( LoginActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT ).show();

                }else if(C_Password.equals( "" )){

                    Toast.makeText( LoginActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT ).show();

                }else{

                    classic_spinner.setVisibility( view.VISIBLE );

                    login_data(C_Id, C_Phone_No,C_Password);
                }

            }
        } );

        btn_cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.exit( 0 );
            }
        } );

        tv_new_registration.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity( intent );
            }
        } );

        tv_forgot_password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity( intent );
            }
        } );
    }

    private void login_data(int c_id, String c_Phone_No, String c_Password){

        api = RetrofitApi.getClient(this).create( APIInterface.class );

        Call<LoginModel> loginModelCall = api.login( c_id, c_Phone_No,c_Password );

        loginModelCall.enqueue( new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if(response.body().getSuccess()){

                    if(cb_rememberme.isChecked()){

                        editor.putBoolean( "id", true );
                        editor.commit();

                        classic_spinner.setVisibility( View.GONE );

                        Intent intent = new Intent(LoginActivity.this,NavigationActivity.class);
                        startActivity( intent );
                        finish();

                    }else{

                        classic_spinner.setVisibility( View.GONE );

                        Intent intent = new Intent(LoginActivity.this,NavigationActivity.class);
                        startActivity( intent );
                        finish();
                    }

                }else{

                    classic_spinner.setVisibility( View.GONE );

                    Toast.makeText( LoginActivity.this, "Please Enter Valid Phone Number And Password", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                classic_spinner.setVisibility( View.GONE );

                Toast.makeText( LoginActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}