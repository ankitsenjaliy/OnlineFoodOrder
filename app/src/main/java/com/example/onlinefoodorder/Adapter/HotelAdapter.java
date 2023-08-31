package com.example.onlinefoodorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinefoodorder.Activity.AllFoodsActivity;
import com.example.onlinefoodorder.Model.HotelModel2;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    List<HotelModel2> hotel_model;
    Context context;

    public HotelAdapter(Context context, List<HotelModel2> hotels){

        this.context = context;
        this.hotel_model = hotels;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.recycler_view_hotel, viewGroup, false );
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position){

        HotelModel2 hotelModel2 = hotel_model.get( position );

        Glide.with( viewHolder.iv_hotel.getContext() )
                .load( hotelModel2.getHotelImage() )
                .placeholder( R.color.orange )
//                .override( 512,512 )
                .into( viewHolder.iv_hotel );

        viewHolder.tv_hotel_name.setText( ""+hotelModel2.getHotelName() );
        viewHolder.tv_hotel_discount.setText( ""+hotelModel2.getHotelDiscount() );


        viewHolder.btn_order_now.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AllFoodsActivity.class);
                intent.putExtra( "H_Name",hotelModel2.getHotelName() );
                context.startActivity( intent );
            }
        } );

        viewHolder.cv_card_view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AllFoodsActivity.class);
                intent.putExtra( "H_Name",hotelModel2.getHotelName() );
                context.startActivity( intent );
            }
        } );
    }

    public int getItemCount(){

        return hotel_model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cv_card_view;
        ImageView iv_hotel;
        TextView tv_hotel_name,tv_hotel_discount;
        MaterialButton btn_order_now;


        public ViewHolder(View itemView){

            super(itemView);

            cv_card_view = itemView.findViewById( R.id.cv_card_view );
            iv_hotel = itemView.findViewById( R.id.iv_hotel );
            tv_hotel_name = itemView.findViewById( R.id.tv_hotel_name );
            tv_hotel_discount = itemView.findViewById( R.id.tv_hotel_discount );
            btn_order_now = itemView.findViewById( R.id.btn_order_now );
        }
    }

}
