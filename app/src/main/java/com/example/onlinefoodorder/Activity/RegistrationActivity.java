package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.RegistrationModel;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_name, et_phone_no, et_email_id, et_password, et_confirm_password, et_address;
    MaterialButton btn_register;

    MKLoader five_pulse;

    Integer Customer_Id;
    String C_Name,C_Phone_No,C_Email_Id,C_Password,C_Confirm_Password,C_Address;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );

        et_name = findViewById( R.id.et_name );
        et_phone_no = findViewById( R.id.et_phone_no );
        et_email_id = findViewById( R.id.et_email_id );
        et_password = findViewById( R.id.et_password );
        et_confirm_password = findViewById( R.id.et_confirm_password );
        et_address = findViewById( R.id.et_address );

        btn_register = findViewById( R.id.btn_register );

        five_pulse = findViewById( R.id.five_pulse );

        sharedPreferences = getSharedPreferences( "mypreference", Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();

        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                C_Name = et_name.getText().toString();
                C_Phone_No = et_phone_no.getText().toString();
                C_Email_Id = et_email_id.getText().toString();
                C_Password = et_password.getText().toString();
                C_Confirm_Password = et_confirm_password.getText().toString();
                C_Address = et_address.getText().toString();

                if(C_Name.equals( "" )){

                    Toast.makeText( RegistrationActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT ).show();

                }else if(C_Phone_No.equals( "" )){

                    Toast.makeText( RegistrationActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT ).show();

                }else if(C_Email_Id.equals( "" )){

                    Toast.makeText( RegistrationActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT ).show();

                }else if(!C_Email_Id.contains( "@" ) || !C_Email_Id.contains( ".com" )){

                    Toast.makeText( RegistrationActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT ).show();

                }else if(C_Password.equals( "" )){

                    Toast.makeText( RegistrationActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT ).show();

                }else if(C_Confirm_Password.equals( "" )){

                    Toast.makeText( RegistrationActivity.this, "Please Re Enter Your Password", Toast.LENGTH_SHORT ).show();

                }else if(!C_Password.equals( C_Confirm_Password )){

                    Toast.makeText( RegistrationActivity.this, "Password Do Not Match", Toast.LENGTH_SHORT ).show();
                    
                }else if(C_Address.equals( "" )){

                    Toast.makeText( RegistrationActivity.this, "Please Enter Your Address", Toast.LENGTH_SHORT ).show();

                }else{

                    five_pulse.setVisibility( view.VISIBLE );

                    registration_data(C_Name,C_Phone_No,C_Email_Id,C_Password,C_Address);

                }
            }
        } );
    }

    private void registration_data(String c_Name, String c_Phone_No, String c_Email_Id, String c_Password, String c_Address) {

        api = RetrofitApi.getClient(this).create(APIInterface.class);

        Call<RegistrationModel> registerModelCall = api.register(c_Name,c_Phone_No,c_Email_Id,c_Password,c_Address );
        registerModelCall.enqueue( new Callback<RegistrationModel>() {
            @Override
            public void onResponse(Call<RegistrationModel> call, Response<RegistrationModel> response) {

                if (response.body().getSuccess()){

                    five_pulse.setVisibility( View.GONE );

                    Intent intent = new Intent(RegistrationActivity.this, NavigationActivity.class);
                    startActivity( intent );
                    finish();

                    Customer_Id = response.body().getCustomerId();
                    editor.putInt( "Customer_Id",Customer_Id);

                    editor.commit();

                    Toast.makeText( RegistrationActivity.this, "Registration SuccessFully", Toast.LENGTH_SHORT ).show();

                }else{

                    five_pulse.setVisibility( View.GONE );

                    Toast.makeText( RegistrationActivity.this, "Email Id And Phone Number Already Used!", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t) {

                five_pulse.setVisibility( View.GONE );

                Toast.makeText( RegistrationActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}