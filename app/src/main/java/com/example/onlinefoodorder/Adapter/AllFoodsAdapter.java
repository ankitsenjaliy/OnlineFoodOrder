package com.example.onlinefoodorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinefoodorder.Activity.SingleFoodActivity;
import com.example.onlinefoodorder.Model.AllFood2;
import com.example.onlinefoodorder.R;

import java.util.List;

public class AllFoodsAdapter extends RecyclerView.Adapter<AllFoodsAdapter.ViewHolder> {

    List<AllFood2> food_model;
    Context context;

    public AllFoodsAdapter(Context context, List<AllFood2> foods){

        this.context = context;
        this.food_model = foods;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.recycler_view_all_foods, viewGroup, false );
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position){

        AllFood2 allFood2 = food_model.get( position );

        Glide.with( viewHolder.iv_food_image.getContext() )
                .load( allFood2.getFoodImage() )
                .placeholder( R.color.orange )
//                .override( 512,512 )
                .into( viewHolder.iv_food_image );

        viewHolder.tv_food_name.setText( ""+allFood2.getFoodName() );
        viewHolder.tv_food_price.setText( "₹ "+allFood2.getFoodPrice() );

        viewHolder.cv_card_view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SingleFoodActivity.class );
                intent.putExtra( "F_Id", allFood2.getFoodId() );
                intent.putExtra( "H_Name", allFood2.getHotelName() );
                intent.putExtra( "F_Name",allFood2.getFoodName() );
                intent.putExtra( "F_Price", allFood2.getFoodPrice() );
                intent.putExtra( "F_Description", allFood2.getFoodDescription() );
                intent.putExtra( "F_Image", allFood2.getFoodImage() );
                context.startActivity( intent );
            }
        } );

        viewHolder.iv_add_to_cart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SingleFoodActivity.class );
                intent.putExtra( "F_Id", allFood2.getFoodId() );
                intent.putExtra( "H_Name", allFood2.getHotelName() );
                intent.putExtra( "F_Name",allFood2.getFoodName() );
                intent.putExtra( "F_Price", allFood2.getFoodPrice() );
                intent.putExtra( "F_Description", allFood2.getFoodDescription() );
                intent.putExtra( "F_Image", allFood2.getFoodImage() );
                context.startActivity( intent );
            }
        } );

    }

    public int getItemCount(){

        return food_model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cv_card_view;
        ImageView iv_food_image,iv_add_to_cart;
        TextView tv_food_name,tv_food_price;

        public ViewHolder(View itemView){

            super(itemView);

            cv_card_view = itemView.findViewById( R.id.cv_card_view );
            iv_food_image = itemView.findViewById( R.id.iv_food_image );
            tv_food_name = itemView.findViewById( R.id.tv_food_name );
            tv_food_price = itemView.findViewById( R.id.tv_food_price );
            iv_add_to_cart = itemView.findViewById( R.id.iv_add_to_cart );
        }
    }

}