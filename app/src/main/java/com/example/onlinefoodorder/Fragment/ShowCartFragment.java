package com.example.onlinefoodorder.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinefoodorder.Adapter.ShowCartAdapter;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.DeleteCartInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.DeleteCartFood;
import com.example.onlinefoodorder.Model.ShowCartModel1;
import com.example.onlinefoodorder.Model.ShowCartModel2;
import com.example.onlinefoodorder.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCartFragment extends Fragment  {

    ImageView iv_delete;
    RecyclerView rv_add_to_cart;
    List<ShowCartModel2> showCartModel2List = new ArrayList<>();

    Integer Customer_Id;
    MKLoader whirlpool;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DeleteCartInterface deleteCartInterface;

    APIInterface api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate( R.layout.fragment_add_show, container, false );


        rv_add_to_cart = view.findViewById( R.id.rv_add_to_cart );
        whirlpool = view.findViewById( R.id.whirlpool );
        iv_delete = view.findViewById( R.id.iv_delete );

        sharedPreferences = getContext().getSharedPreferences( "mypreference", Context.MODE_PRIVATE );
        editor = sharedPreferences.edit();

        whirlpool.setVisibility( View.VISIBLE );

        Customer_Id = sharedPreferences.getInt( "Customer_Id", 0 );

        show_cart_data(Customer_Id);

        deleteCartInterface = new DeleteCartInterface() {
            @Override
            public void onDeleteFoodCart(String F_Id) {

                whirlpool.setVisibility( View.VISIBLE );

                api = RetrofitApi.getClient( getContext() ).create( APIInterface.class );

                Call<DeleteCartFood> deleteCartFoodCall = api.delete_cart_food( F_Id );

                deleteCartFoodCall.enqueue( new Callback<DeleteCartFood>() {
                    @Override
                    public void onResponse(Call<DeleteCartFood> call, Response<DeleteCartFood> response) {

                        if(response.body().getSuccess()){

                            whirlpool.setVisibility( View.GONE );

                            show_cart_data(Customer_Id);

                            Toast.makeText( getContext(), "Delete SuccessFully", Toast.LENGTH_SHORT ).show();

                        } else {

                            whirlpool.setVisibility( View.GONE );

                            Toast.makeText( getContext(), "Not Deleted", Toast.LENGTH_SHORT ).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<DeleteCartFood> call, Throwable t) {

                        whirlpool.setVisibility( View.GONE );

                        Toast.makeText( getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

                    }
                } );
            }
        };

        return view;
    }

    private void show_cart_data(Integer customer_Id){

        api = RetrofitApi.getClient( getContext() ).create( APIInterface.class );

        Call<ShowCartModel1> showCartModel1Call = api.show_cart( customer_Id );

        showCartModel1Call.enqueue( new Callback<ShowCartModel1>() {
            @Override
            public void onResponse(Call<ShowCartModel1> call, Response<ShowCartModel1> response) {

                if(response.body().getSuccess()){

                    whirlpool.setVisibility( View.GONE );

                    showCartModel2List = response.body().getData();

                    details_data(showCartModel2List);

                }else{

                    whirlpool.setVisibility( View.GONE );

                    Toast.makeText( getContext(), "Not Available Food In Cart", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call<ShowCartModel1> call, Throwable t) {

                whirlpool.setVisibility( View.GONE );

                Toast.makeText( getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    public void onResume() {
        super.onResume();

        whirlpool.setVisibility( View.VISIBLE );
        details_data(showCartModel2List);

    }

    @Override
    public void onStart() {
        super.onStart();

        whirlpool.setVisibility( View.VISIBLE );
        details_data(showCartModel2List);
    }

    private void details_data(List<ShowCartModel2> showCartModel2List) {

        ShowCartAdapter showCartAdapter = new ShowCartAdapter( showCartModel2List, getContext(), deleteCartInterface );

        GridLayoutManager gridLayoutManager = new GridLayoutManager( getContext(), 1 );
        rv_add_to_cart.setLayoutManager( gridLayoutManager );

        rv_add_to_cart.setAdapter( showCartAdapter );
    }
}