package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.AllCustomerData;
import com.example.onlinefoodorder.Model.AllDataCustomer;
import com.example.onlinefoodorder.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ImageView iv_edit, iv_back_arrow;
    TextView tv_name,tv_phone_no,tv_email_id,tv_address;

    MKLoader twin_fishes_spinner;

    Integer Customer_Id;
    String Customer_Name,Customer_Phone_No,Customer_Email_Id,Customer_Address;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    APIInterface api;
    List<AllCustomerData> allCustomerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        iv_edit = findViewById( R.id.iv_edit );
        iv_back_arrow = findViewById( R.id.iv_back_arrow );

        twin_fishes_spinner = findViewById( R.id.twin_fishes_spinner );

        tv_name = findViewById( R.id.tv_name );
        tv_phone_no = findViewById( R.id.tv_phone_no );
        tv_email_id = findViewById( R.id.tv_email_id );
        tv_address = findViewById( R.id.tv_address );

        sharedPreferences = getSharedPreferences( "mypreference", MODE_PRIVATE );
        editor = sharedPreferences.edit();

      /*  Customer_Name = sharedPreferences.getString( "Customer_Name",Customer_Name );
        Customer_Phone_No = sharedPreferences.getString( "Customer_Phone_No",Customer_Phone_No );
        Customer_Email_Id = sharedPreferences.getString( "Customer_Email_Id",Customer_Email_Id );
        Customer_Address = sharedPreferences.getString( "Customer_Address",Customer_Address );

        tv_name.setText( Customer_Name );
        tv_phone_no.setText( Customer_Phone_No );
        tv_email_id.setText( Customer_Email_Id );
        tv_address.setText( Customer_Address );*/

        twin_fishes_spinner.setVisibility( View.VISIBLE );

        Customer_Id = sharedPreferences.getInt( "Customer_Id",0 );

        UserDetails();

        iv_edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Customer_Name = tv_name.getText().toString();
                Customer_Phone_No = tv_phone_no.getText().toString();
                Customer_Email_Id = tv_email_id.getText().toString();
                Customer_Address = tv_address.getText().toString();

                Intent intent = new Intent(ProfileActivity.this, ProfileUpdateActivity.class);
                intent.putExtra( "Customer_Id",Customer_Id );
                intent.putExtra( "Customer_Name",Customer_Name );
                intent.putExtra( "Customer_Phone_No",Customer_Phone_No );
                intent.putExtra( "Customer_Email_Id",Customer_Email_Id );
                intent.putExtra( "Customer_Address",Customer_Address );
                startActivity( intent );

            }
        } );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );
    }

    private void UserDetails() {

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<AllDataCustomer> allDataCustomerCall = api.customer( Customer_Id );

        allDataCustomerCall.enqueue( new Callback<AllDataCustomer>() {
            @Override
            public void onResponse(Call<AllDataCustomer> call, Response<AllDataCustomer> response) {

                if(response.body().getSuccess()){

                    twin_fishes_spinner.setVisibility( View.GONE );

                    allCustomerData = response.body().getData();

                    tv_name.setText( allCustomerData.get( 0 ).getCustomerName() );
                    tv_phone_no.setText( allCustomerData.get( 0 ).getCustomerPhoneNo() );
                    tv_email_id.setText( allCustomerData.get( 0 ).getCustomerEmailId() );
                    tv_address.setText( allCustomerData.get( 0 ).getCustomerAddress() );

                }else{

                    twin_fishes_spinner.setVisibility( View.GONE );

                    Toast.makeText( ProfileActivity.this, "Data Not Found", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onFailure(Call<AllDataCustomer> call, Throwable t) {

                twin_fishes_spinner.setVisibility( View.GONE );

                Toast.makeText( ProfileActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );

    }
}