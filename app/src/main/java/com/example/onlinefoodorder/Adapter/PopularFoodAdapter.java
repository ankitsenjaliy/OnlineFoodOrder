package com.example.onlinefoodorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinefoodorder.Activity.PopularAllFoodActivity;
import com.example.onlinefoodorder.Model.PopularFoodModel2;
import com.example.onlinefoodorder.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.ViewHolder> {

    List<PopularFoodModel2> popular_food;
    Context context;

    public PopularFoodAdapter(Context context, List<PopularFoodModel2> food_model){

        this.context = context;
        this.popular_food = food_model;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.recycler_view_popular_food, viewGroup, false );
        return new ViewHolder(view);
    }

    public void onBindViewHolder(PopularFoodAdapter.ViewHolder viewHolder, int position){

        PopularFoodModel2 popularFoodModel2 = popular_food.get( position );

        Glide.with(viewHolder.civ_popular_foods_image.getContext())
                .load( popularFoodModel2.getItemFoodImage() )
                .placeholder( R.color.orange )
//                .override( 512,512 )
//                .fitCenter()
                .into( viewHolder.civ_popular_foods_image );

        viewHolder.tv_popular_foods_name.setText(""+popularFoodModel2.getItemFoodName());

        viewHolder.civ_popular_foods_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PopularAllFoodActivity.class );
                intent.putExtra( "I_Food_Name", popularFoodModel2.getItemFoodName() );
                context.startActivity( intent );
            }
        } );
    }

    public int getItemCount(){

        return popular_food.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView civ_popular_foods_image;
        TextView tv_popular_foods_name;

        public ViewHolder(View itemView){
            super(itemView);

            civ_popular_foods_image = itemView.findViewById( R.id.civ_popular_foods_image );
            tv_popular_foods_name = itemView.findViewById( R.id.tv_popular_foods_name );

        }
    }

}
