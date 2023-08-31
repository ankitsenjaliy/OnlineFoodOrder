package com.example.onlinefoodorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Fragment.ShowCartFragment;
import com.example.onlinefoodorder.Fragment.HomeFragment;
import com.example.onlinefoodorder.Model.AllCustomerData;
import com.example.onlinefoodorder.Model.AllDataCustomer;
import com.example.onlinefoodorder.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationActivity extends AppCompatActivity {

    ImageView three_line;
    ActionBarDrawerToggle toggle;
    boolean doubleBackToExitPressedOnce = false;

    DrawerLayout drawer_layout;
    Toolbar tool_bar;
    NavigationView navigation_view;
    TextView tv_text;

    View header;
    TextView tv_name,tv_email_id;

    MeowBottomNavigation bottom_navigation;
    FrameLayout frame_layout;

    Integer Customer_Id;
    String Customer_Name,Customer_Email_Id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    APIInterface api;
    List<AllCustomerData> allCustomerData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_navigation );

        three_line = findViewById( R.id.three_line );

        drawer_layout = findViewById( R.id.drawer_layout );
        tool_bar = findViewById( R.id.tool_bar );
        navigation_view = findViewById( R.id.navigation_view );

        bottom_navigation = findViewById( R.id.bottom_navigation );
        frame_layout = findViewById( R.id.frame_layout );

        tv_text = findViewById( R.id.tv_text );

        sharedPreferences = getSharedPreferences( "mypreference",MODE_PRIVATE );
        editor = sharedPreferences.edit();

        header = navigation_view.getHeaderView( 0 );

        tv_name = header.findViewById( R.id.tv_name );
        tv_email_id = header.findViewById( R.id.tv_email_id );

        Customer_Name = sharedPreferences.getString( "Customer_Name",Customer_Name );
//        Customer_Phone_No = sharedPreferences.getString( "Customer_Phone_No", Customer_Phone_No);
        Customer_Email_Id = sharedPreferences.getString( "Customer_Email_Id",Customer_Email_Id );
//        Customer_Password = sharedPreferences.getString( "Customer_Password", Customer_Password);
//        Customer_Address = sharedPreferences.getString( "Customer_Address",Customer_Address );

        tv_name.setText( Customer_Name );
        tv_email_id.setText( Customer_Email_Id );

        Customer_Id = sharedPreferences.getInt( "Customer_Id",0 );

        UserDetails();

        toggle = new ActionBarDrawerToggle( this, drawer_layout, tool_bar, R.string.Open, R.string.Close);
        toggle.setDrawerIndicatorEnabled( false );

        three_line.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(drawer_layout.isDrawerOpen( GravityCompat.START )){

                    drawer_layout.closeDrawer( GravityCompat.START );

                }else{
                    drawer_layout.openDrawer( GravityCompat.START );
                }
            }
        } );

        bottom_navigation.add( new MeowBottomNavigation.Model( 1, R.drawable.cart ) );
        bottom_navigation.add( new MeowBottomNavigation.Model( 2, R.drawable.home ) );
        bottom_navigation.add( new MeowBottomNavigation.Model( 3, R.drawable.wishlist ) );

        bottom_navigation.setOnShowListener( new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                int id = item.getId();

                if(id == 1){

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new ShowCartFragment() ).commit();
                    tv_text.setText( "Your Cart" );

                } else if (id == 2){

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new HomeFragment() ).commit();
                    tv_text.setText( "Online Food Order" );

                } else if (id == 3){

//                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new ProfileFragment() ).commit();
//                    tv_text.setText( "Your Profile" );
                }

            }
        } );

        bottom_navigation.show( 2, true );

        bottom_navigation.setOnClickMenuListener( new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

//                Toast.makeText( getApplicationContext()
//                        , "" + item.getId()
//                        , Toast.LENGTH_SHORT  ).show();
            }
        } );

        bottom_navigation.setOnReselectListener( new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

//                Toast.makeText( getApplicationContext()
//                      , "You Reslected" + item.getId()
//                      , Toast.LENGTH_SHORT).show();
            }
        } );


        getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new HomeFragment() ).commit();
        tv_text.setText( "Online Food Order" );

        navigation_view.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.home){

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new HomeFragment() ).commit();
                    tv_text.setText( "Online Food Order" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                }else if(id == R.id.profile){

                    Intent intent = new Intent(NavigationActivity.this,ProfileActivity.class);
                    startActivity( intent );

                    drawer_layout.closeDrawer( GravityCompat.START );

                }
//                else if(id == R.id.order){
//
//
//                }
                else if(id == R.id.settings){

                    Intent intent = new Intent(NavigationActivity.this, SettingsActivity.class);
                    startActivity( intent );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if(id == R.id.about_us){

                    Intent intent = new Intent(NavigationActivity.this, AboutUsActivity.class);
                    startActivity( intent );

                } else if(id == R.id.logout){

                    logout();

                }

                return true;
            }

            public void logout(){

                sharedPreferences = getSharedPreferences( "mypreference",Context.MODE_PRIVATE );
                editor = sharedPreferences.edit();

                Toast.makeText( NavigationActivity.this, "Logout SuccessFully", Toast.LENGTH_SHORT ).show();

                editor.remove( "id" );
                editor.commit();
                finish();
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

                    allCustomerData = response.body().getData();

                    tv_name.setText( allCustomerData.get( 0 ).getCustomerName() );
                    tv_email_id.setText( allCustomerData.get( 0 ).getCustomerEmailId() );

                }else{

                    Toast.makeText( NavigationActivity.this, "Data Not Found", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onFailure(Call<AllDataCustomer> call, Throwable t) {

                Toast.makeText( NavigationActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );

    }


    public void onBackPressed(){

        if(doubleBackToExitPressedOnce){
            finish();
        }

        this.doubleBackToExitPressedOnce = true;

        Toast.makeText( this,getResources().getString( R.string.Please_Click_Back_Again_To_Exit ), Toast.LENGTH_SHORT ).show();

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}