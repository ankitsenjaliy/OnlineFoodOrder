package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinefoodorder.Adapter.AllFoodsAdapter;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.AllFood1;
import com.example.onlinefoodorder.Model.AllFood2;
import com.example.onlinefoodorder.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFoodsActivity extends AppCompatActivity {

    ImageView iv_back_arrow;
    MKLoader classic_spinner;

    RecyclerView rv_all_foods;
    List<AllFood2> allFood2List = new ArrayList<>();

    String H_Name;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_foods );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );
        classic_spinner = findViewById( R.id.classic_spinner );

        rv_all_foods = findViewById( R.id.rv_all_foods );

        H_Name = getIntent().getStringExtra( "H_Name" );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );

        classic_spinner.setVisibility( View.VISIBLE );

        all_food_data();

    }

    private void all_food_data(){

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<AllFood1> allFood1Call = api.all_foods(H_Name);

        allFood1Call.enqueue( new Callback<AllFood1>() {
            @Override
            public void onResponse(Call<AllFood1> call, Response<AllFood1> response) {

                if(response.body().getSuccess()){

                    classic_spinner.setVisibility( View.GONE );

                    allFood2List = response.body().getData();

                    AllFoodsAdapter allFoodsAdapter = new AllFoodsAdapter( AllFoodsActivity.this, allFood2List );

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(AllFoodsActivity.this,2 );
                    rv_all_foods.setLayoutManager( gridLayoutManager );

                    rv_all_foods.setAdapter( allFoodsAdapter );

                }else{

                    classic_spinner.setVisibility( View.GONE );

                    onBackPressed();

                    Toast.makeText( AllFoodsActivity.this, "Not Available Food", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<AllFood1> call, Throwable t) {

                classic_spinner.setVisibility( View.GONE );

                Toast.makeText( AllFoodsActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}