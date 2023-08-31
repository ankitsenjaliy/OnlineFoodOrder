package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinefoodorder.Adapter.PopularAllFoodAdapter;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.PopularAllFood1;
import com.example.onlinefoodorder.Model.PopularAllFood2;
import com.example.onlinefoodorder.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularAllFoodActivity extends AppCompatActivity {

    ImageView iv_back_arrow;
    MKLoader classic_spinner;

    RecyclerView rv_popular_all_foods;
    List<PopularAllFood2> popularAllFood2List = new ArrayList<>();

    String I_Food_Name;

    APIInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_popular_all_food );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );
        classic_spinner = findViewById( R.id.classic_spinner );

        rv_popular_all_foods = findViewById( R.id.rv_popular_all_foods );

        I_Food_Name = getIntent().getStringExtra( "I_Food_Name" );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );


        classic_spinner.setVisibility( View.VISIBLE );

        all_popular_food_data();
    }

    private void all_popular_food_data(){

        api = RetrofitApi.getClient( this ).create( APIInterface.class );

        Call<PopularAllFood1> popularAllFood1Call = api.popular_all_foods( I_Food_Name );

        popularAllFood1Call.enqueue( new Callback<PopularAllFood1>() {
            @Override
            public void onResponse(Call<PopularAllFood1> call, Response<PopularAllFood1> response) {

                if(response.body().getSuccess()){

                    classic_spinner.setVisibility( View.GONE );

                    popularAllFood2List = response.body().getData();

                    PopularAllFoodAdapter popularAllFoodAdapter = new PopularAllFoodAdapter( PopularAllFoodActivity.this, popularAllFood2List );

                    GridLayoutManager gridLayoutManager = new GridLayoutManager( PopularAllFoodActivity.this, 2 );
                    rv_popular_all_foods.setLayoutManager( gridLayoutManager );

                    rv_popular_all_foods.setAdapter( popularAllFoodAdapter );

                }else{

                    classic_spinner.setVisibility( View.GONE );

                    onBackPressed();

                    Toast.makeText( PopularAllFoodActivity.this, "Not Available Food", Toast.LENGTH_SHORT ).show();

                }

            }

            @Override
            public void onFailure(Call<PopularAllFood1> call, Throwable t) {

                classic_spinner.setVisibility( View.GONE );

                Toast.makeText( PopularAllFoodActivity.this, "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );

    }
}