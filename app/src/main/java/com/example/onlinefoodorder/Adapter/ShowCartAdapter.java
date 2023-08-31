package com.example.onlinefoodorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinefoodorder.Activity.AddCartActivity;
import com.example.onlinefoodorder.Activity.NavigationActivity;
import com.example.onlinefoodorder.Api.APIInterface;
import com.example.onlinefoodorder.Api.DeleteCartInterface;
import com.example.onlinefoodorder.Api.RetrofitApi;
import com.example.onlinefoodorder.Model.DeleteCartFood;
import com.example.onlinefoodorder.Model.ShowCartModel2;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCartAdapter extends RecyclerView.Adapter<ShowCartAdapter.ViewHolder> {

    List<ShowCartModel2> show_cart;
    Context context;

    DeleteCartInterface deleteCartInterface;

    public ShowCartAdapter(List<ShowCartModel2> show_cart, Context context, DeleteCartInterface deleteCartInterface) {
        this.show_cart = show_cart;
        this.context = context;
        this.deleteCartInterface = deleteCartInterface;
    }

    public ShowCartAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.recycler_add_to_cart, viewGroup, false );
        return new ShowCartAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(ShowCartAdapter.ViewHolder viewHolder, int position){

        ShowCartModel2 showCartModel2 = show_cart.get( position );

        Glide.with( viewHolder.iv_food_image.getContext() )
                .load( ""+ showCartModel2.getFoodImage())
                .placeholder( R.color.lakda )
                .into( viewHolder.iv_food_image );

        viewHolder.tv_food_name.setText( ""+showCartModel2.getFoodName() );
        viewHolder.tv_food_price.setText( "₹ "+showCartModel2.getFoodPrice() );


        viewHolder.btn_buy_now.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AddCartActivity.class );
                intent.putExtra( "F_Id",""+showCartModel2.getFoodId() );
                intent.putExtra( "F_Name",""+showCartModel2.getFoodName());
                intent.putExtra( "F_Price", ""+showCartModel2.getFoodPrice() );
                intent.putExtra( "F_Image", ""+showCartModel2.getFoodImage() );
                context.startActivity( intent );
            }
        } );

        viewHolder.iv_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteCartInterface.onDeleteFoodCart( showCartModel2.getFoodId() );

                notifyDataSetChanged();
            }
        } );
    }

    public int getItemCount(){

        return show_cart.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_food_image,iv_delete;
        TextView tv_food_name,tv_food_price;
        MaterialButton btn_buy_now;

        public ViewHolder(View itemView){

            super(itemView);

            iv_food_image = itemView.findViewById( R.id.iv_food_image );
            tv_food_name = itemView.findViewById( R.id.tv_food_name );
            tv_food_price = itemView.findViewById( R.id.tv_food_price );
            btn_buy_now = itemView.findViewById( R.id.btn_buy_now );
            iv_delete = itemView.findViewById( R.id.iv_delete );
        }
    }
}
