package com.example.onlinefoodorder.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.onlinefoodorder.Adapter.HomeViewPagerAdapter;
import com.example.onlinefoodorder.Adapter.PopularFoodAdapter;
import com.example.onlinefoodorder.Adapter.HotelAdapter;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.HomeViewPager;
import com.example.onlinefoodorder.Model.PopularFoodModel1;
import com.example.onlinefoodorder.Model.PopularFoodModel2;
import com.example.onlinefoodorder.Model.HotelModel1;
import com.example.onlinefoodorder.Model.HotelModel2;
import com.example.onlinefoodorder.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

//    View Pager

    ViewPager2 vp_view_pager2;
    List<HomeViewPager> homeViewPagerList;
    HomeViewPagerAdapter homeViewPagerAdapter;

//    Popular Food

    RecyclerView rv_popular_foods;
    List<PopularFoodModel2> popularFoodModel2List = new ArrayList<>();

//    Restaurant
      RecyclerView rv_hotels;
      List<HotelModel2> hotelModel2List = new ArrayList<>();

    MKLoader classic_spinner;
    APIInterface api;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate( R.layout.fragment_home, container, false );

        classic_spinner = view.findViewById( R.id.classic_spinner );

//        View Pager
        vp_view_pager2 = view.findViewById( R.id.vp_view_pager2 );

        homeViewPagerList = new ArrayList<>();

        homeViewPagerList.add( new HomeViewPager(R.drawable.offer1) );
        homeViewPagerList.add( new HomeViewPager( R.drawable.offer2 ) );
        homeViewPagerList.add( new HomeViewPager( R.drawable.offer3 ) );
        homeViewPagerList.add( new HomeViewPager( R.drawable.offer4 ) );

        homeViewPagerAdapter = new HomeViewPagerAdapter( homeViewPagerList, vp_view_pager2 );
        vp_view_pager2.setAdapter( homeViewPagerAdapter );

        vp_view_pager2.setOffscreenPageLimit( 3 );
        vp_view_pager2.setClipChildren( false );
        vp_view_pager2.setClipToPadding( false );

        vp_view_pager2.getChildAt( 0 ).setOverScrollMode( RecyclerView.OVER_SCROLL_NEVER );

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer( new MarginPageTransformer( 40 ) );
        compositePageTransformer.addTransformer( new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r = 1 - Math.abs( position );
                page.setScaleY( 0.85f + r * 0.14f );
            }
        } );

        vp_view_pager2.setPageTransformer( compositePageTransformer );

        vp_view_pager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected( position );
                handler.removeCallbacks( runnable );
                handler.postDelayed( runnable,1500 );
            }
        } );


//        Popular Food

        rv_popular_foods = view.findViewById( R.id.rv_popular_foods );

        classic_spinner.setVisibility( view.VISIBLE );

        popular_food_data();


//        Restaurant

        rv_hotels = view.findViewById( R.id.rv_hotels );

        classic_spinner.setVisibility( view.VISIBLE );

        hotel_data();

         return view;
    }


//    View Pager

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            vp_view_pager2.setCurrentItem( vp_view_pager2.getCurrentItem() + 1 );
        }
    };

    @Override
    public void onPause() {
        super.onPause();

        handler.removeCallbacks( runnable );
    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed( runnable, 1500 );
    }


//    Popular Food

    private void popular_food_data(){

        api = RetrofitApi.getClient( getContext() ).create( APIInterface.class );

        Call<PopularFoodModel1>  popularFoodModelCall = api.popularFood();

        popularFoodModelCall.enqueue( new Callback<PopularFoodModel1>() {
            @Override
            public void onResponse(Call<PopularFoodModel1> call, Response<PopularFoodModel1> response) {

                classic_spinner.setVisibility( View.GONE );

                popularFoodModel2List = response.body().getData();

                PopularFoodAdapter popularFoodAdapter = new PopularFoodAdapter(getContext(), popularFoodModel2List);

                GridLayoutManager gridLayoutManager = new GridLayoutManager( getContext(), 3 );
                rv_popular_foods.setLayoutManager( gridLayoutManager );

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext(),LinearLayoutManager.HORIZONTAL,false );
                rv_popular_foods.setLayoutManager( linearLayoutManager );

                rv_popular_foods.setAdapter( popularFoodAdapter );

            }

            @Override
            public void onFailure(Call<PopularFoodModel1> call, Throwable t) {

                classic_spinner.setVisibility( View.GONE );

                Toast.makeText( getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );
    }

    private void hotel_data(){

        api = RetrofitApi.getClient(getContext()).create(APIInterface.class);

        Call<HotelModel1> hotelModel1Call = api.restaurant();

        hotelModel1Call.enqueue( new Callback<HotelModel1>() {
            @Override
            public void onResponse(Call<HotelModel1> call, Response<HotelModel1> response) {

                classic_spinner.setVisibility( View.GONE );

                hotelModel2List = response.body().getData();

                HotelAdapter hotelAdapter = new HotelAdapter( getContext(), hotelModel2List );

                GridLayoutManager gridLayoutManager = new GridLayoutManager( getContext(),1 );
                rv_hotels.setLayoutManager( gridLayoutManager );

                rv_hotels.setAdapter( hotelAdapter );

            }

            @Override
            public void onFailure(Call<HotelModel1> call, Throwable t) {

                Toast.makeText( getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT ).show();

            }
        } );
    }

}