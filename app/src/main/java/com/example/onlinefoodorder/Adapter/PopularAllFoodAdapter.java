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
import com.example.onlinefoodorder.Activity.SingleFoodActivity;
import com.example.onlinefoodorder.Model.PopularAllFood2;
import com.example.onlinefoodorder.R;

import java.util.List;

public class PopularAllFoodAdapter extends RecyclerView.Adapter<PopularAllFoodAdapter.ViewHolder> {

    List<PopularAllFood2> popular_foods;
    Context context;

    public PopularAllFoodAdapter(Context context, List<PopularAllFood2> all_foods){

        this.context = context;
        this.popular_foods = all_foods;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.recycler_view_popular_all_food, viewGroup, false );
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position){

        PopularAllFood2 popularAllFood2 = popular_foods.get( position );

        viewHolder.tv_hotel_name.setText( popularAllFood2.getHotelName() );

        Glide.with( viewHolder.iv_food_image.getContext() )
                .load( popularAllFood2.getFoodImage() )
                .placeholder( R.color.orange )
                .into( viewHolder.iv_food_image );

        viewHolder.tv_food_name.setText( popularAllFood2.getFoodName() );
        viewHolder.tv_food_price.setText( "₹ "+popularAllFood2.getFoodPrice() );

        viewHolder.cv_card_view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SingleFoodActivity.class );
                intent.putExtra( "F_Id",popularAllFood2.getFoodId() );
                intent.putExtra( "H_Name",popularAllFood2.getHotelName() );
                intent.putExtra( "F_Name",popularAllFood2.getFoodName() );
                intent.putExtra( "F_Price",popularAllFood2.getFoodPrice() );
                intent.putExtra( "F_Description", popularAllFood2.getFoodDescription() );
                intent.putExtra( "F_Image", popularAllFood2.getFoodImage() );
                context.startActivity( intent );
            }
        } );

        viewHolder.iv_add_to_cart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SingleFoodActivity.class );
                intent.putExtra( "F_Id",popularAllFood2.getFoodId() );
                intent.putExtra( "H_Name",popularAllFood2.getHotelName() );
                intent.putExtra( "F_Name",popularAllFood2.getFoodName() );
                intent.putExtra( "F_Price",popularAllFood2.getFoodPrice() );
                intent.putExtra( "F_Description", popularAllFood2.getFoodDescription() );
                intent.putExtra( "F_Image", popularAllFood2.getFoodImage() );
                context.startActivity( intent );

            }
        } );

    }

    public int getItemCount(){

        return popular_foods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cv_card_view;
        TextView tv_hotel_name,tv_food_name,tv_food_price;
        ImageView iv_food_image,iv_add_to_cart;

        public ViewHolder(View itemView){

            super(itemView);

            cv_card_view = itemView.findViewById( R.id.cv_card_view );
            tv_hotel_name = itemView.findViewById( R.id.tv_hotel_name );
            iv_food_image = itemView.findViewById( R.id.iv_food_image );
            tv_food_name = itemView.findViewById( R.id.tv_food_name );
            tv_food_price = itemView.findViewById( R.id.tv_food_price );
            iv_add_to_cart = itemView.findViewById( R.id.iv_add_to_cart );
        }
    }

}
