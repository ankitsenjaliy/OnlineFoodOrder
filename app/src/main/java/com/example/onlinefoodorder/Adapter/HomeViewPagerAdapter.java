package com.example.onlinefoodorder.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onlinefoodorder.Model.HomeViewPager;
import com.example.onlinefoodorder.R;

import java.util.List;

public class HomeViewPagerAdapter extends RecyclerView.Adapter<HomeViewPagerAdapter.ImageViewHolder>{

    List<HomeViewPager> homeViewPagerList;
    ViewPager2 viewPager2;

    public HomeViewPagerAdapter(List<HomeViewPager> homeViewPagerList, ViewPager2 viewPager2){

        this.homeViewPagerList = homeViewPagerList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.view_pager_home, parent, false );

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull   ImageViewHolder holder, int position) {

        holder.iv_view_pager.setImageResource( homeViewPagerList.get( position ).getImage() );

        if(position == homeViewPagerList.size() - 2){

            viewPager2.post( runnable );
        }
    }

    public int getItemCount(){

        return homeViewPagerList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_view_pager;

        public ImageViewHolder(View view){
            super(view);

            iv_view_pager = view.findViewById( R.id.iv_view_pager );
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            homeViewPagerList.addAll( homeViewPagerList );
            notifyDataSetChanged();
        }
    };
}