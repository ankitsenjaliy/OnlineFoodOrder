package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.UpdateProfileModel;
import com.example.onlinefoodorder.R;
import com.tuyenmonkey.mkloader.MKLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUpdateActivity extends AppCompatActivity {

    ImageView iv_save,iv_back_arrow;
    EditText et_name,et_phone_no,et_email_id, et_address;

    MKLoader twin_fishes_spinner;

    Integer Customer_Id;
    String Customer_Name,Customer_Phone_No,Customer_Email_Id,Customer_Address;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile_update );

        iv_save = findViewById( R.id.iv_save );
        iv_back_arrow = findViewById( R.id.iv_back_arrow );

        twin_fishes_spinner = findViewById( R.id.twin_fishes_spinner );

        et_name = findViewById( R.id.et_name );
        et_phone_no = findViewById( R.id.et_phone_no );
        et_email_id = findViewById( R.id.et_email_id );
        et_address = findViewById( R.id.et_address );

        Customer_Id = getIntent().getIntExtra( "Customer_Id", 0 );

        Customer_Name = getIntent().getStringExtra( "Customer_Name" );
        et_name.setText( Customer_Name );
        Customer_Phone_No = getIntent().getStringExtra( "Customer_Phone_No" );
        et_phone_no.setText( Customer_Phone_No );
        Customer_Email_Id = getIntent().getStringExtra( "Customer_Email_Id" );
        et_email_id.setText( Customer_Email_Id );
        Customer_Address = getIntent().getStringExtra( "Customer_Address" );
        et_address.setText( Customer_Address );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );

        iv_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Customer_Name = et_name.getText().toString();
                Customer_Phone_No = et_phone_no.getText().toString();
                Customer_Email_Id = et_email_id.getText().toString();
                Customer_Address = et_address.getText().toString();

                if(Customer_Name.equals( "" )){

                    Toast.makeText( ProfileUpdateActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT ).show();

                }else if(Customer_Phone_No.equals( "" )){

                    Toast.makeText( ProfileUpdateActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT ).show();

                }else if(Customer_Email_Id.equals( "" )){

                    Toast.makeText( ProfileUpdateActivity.this, "Please Enter Your Email Id", Toast.LENGTH_SHORT ).show();

                }else if(!Customer_Email_Id.contains( ".com" ) || !Customer_Email_Id.contains( "@" )){

                    Toast.makeText( ProfileUpdateActivity.this, "Please Enter Valid Email Id", Toast.LENGTH_SHORT ).show();

                }else if(Customer_Address.equals( "" )){

                    Toast.makeText( ProfileUpdateActivity.this, "Please Enter Your Address", Toast.LENGTH_SHORT ).show();

                }else{

                    twin_fishes_spinner.setVisibility( View.VISIBLE );

                    updateProfile(Customer_Name,Customer_Phone_No,Customer_Email_Id,Customer_Address,Customer_Id);

                }

            }
        } );
    }

    private void updateProfile(String C_Name,String C_Phone_No, String C_Email_Id, String C_Address, Integer C_Id){

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<UpdateProfileModel> updateProfileModelCall = api.update_profile(C_Name,C_Phone_No,C_Email_Id,C_Address,C_Id);

        updateProfileModelCall.enqueue( new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {

                if(response.body().getSuccess()){

                    twin_fishes_spinner.setVisibility( View.GONE );

                    Toast.makeText( ProfileUpdateActivity.this, "Your Profile Updated SuccessFully", Toast.LENGTH_SHORT ).show();

                    Intent intent = new Intent(ProfileUpdateActivity.this, ProfileActivity.class);
                    startActivity( intent );
                    finish();

                }else {

                    twin_fishes_spinner.setVisibility( View.GONE );

                    Toast.makeText( ProfileUpdateActivity.this, "Not Valid Data", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {

                twin_fishes_spinner.setVisibility( View.GONE );

                Toast.makeText( ProfileUpdateActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}