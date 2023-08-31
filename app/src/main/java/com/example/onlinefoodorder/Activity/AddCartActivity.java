package com.example.onlinefoodorder.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.onlinefoodorder.R;
import com.google.android.material.button.MaterialButton;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCartActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    ImageView iv_food_image,iv_back_arrow;
    TextView tv_food_name,tv_food_price,tv_food_no,tv_total,tv_text_charge,tv_delivery_charge,tv_total_price;

    MaterialButton btn_pay;

    int count = 1;

    Integer F_Id;
    String F_Name,F_Price,F_Image;

    Integer Price, Text_Charge = 12, Delivery_Charge = 15;
    Integer Total_Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_cart );

        iv_back_arrow = findViewById( R.id.iv_back_arrow );

        iv_food_image = findViewById( R.id.iv_food_image );
        tv_food_name = findViewById( R.id.tv_food_name );
        tv_food_price = findViewById( R.id.tv_food_price );

        tv_total = findViewById( R.id.tv_total );
        tv_text_charge = findViewById( R.id.tv_text_charge );
        tv_delivery_charge = findViewById( R.id.tv_delivery_charge );
        tv_total_price = findViewById( R.id.tv_total_price );

        tv_food_no = findViewById( R.id.tv_food_no );
        btn_pay = findViewById( R.id.btn_pay );

        iv_back_arrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        } );

        F_Id = getIntent().getIntExtra( "F_Id",0 );

        F_Name = getIntent().getStringExtra( "F_Name" );
        tv_food_name.setText( ""+F_Name );
        F_Price = getIntent().getStringExtra( "F_Price" );
        tv_food_price.setText( "₹ "+F_Price );
        F_Image = getIntent().getStringExtra( "F_Image" );
        Glide.with( this )
                .load( F_Image )
                .placeholder( R.color.orange )
                .into( iv_food_image );

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int totalcount = Integer.parseInt( tv_food_no.getText().toString());
                int prices = Integer.parseInt( F_Price );

                Price =  totalcount * prices;
                tv_total.setText( String.valueOf( "₹ "+ Price) );

                tv_text_charge.setText( "₹ "+ Text_Charge );
                tv_delivery_charge.setText( "₹ "+ Delivery_Charge );

                Total_Price = Price + Text_Charge + Delivery_Charge;
                tv_total_price.setText( "₹ "+Total_Price );

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        tv_food_price.addTextChangedListener( textWatcher );
        tv_food_no.addTextChangedListener( textWatcher );


        btn_pay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sAmount = String.valueOf( Total_Price );
                int amount = Math.round( Float.parseFloat( sAmount ) * 100 );

                Checkout checkout = new Checkout();

                checkout.setKeyID( "rzp_test_M0oS6XulHY91Jw" );
                checkout.setImage( R.drawable.logo );



                JSONObject object = new JSONObject();
                try {

                    object.put( "Name","Online Food Order" );
                    object.put( "Description", "Test Payment" );
//                    object.put( "theme.color", "#0093DD" );
                    object.put( "currency", "INR" );
                    object.put( "amount",  amount );
                    object.put( "prefill.contant", "7624066500" );
                    object.put( "prefill.email", "ankitsenjaliya8672@gmail.com" );

                    checkout.open( AddCartActivity.this,object );

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        } );


    }

    public void increment(View view){

        count++;
        tv_food_no.setText( ""+ count );
    }

    public  void decrement(View view){

        if(count <= 1){

            count = 1;

        }else{

            count--;
            tv_food_no.setText( ""+ count );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        tv_food_price.setText( "₹ "+ F_Price );
        tv_food_no.setText( ""+ count );

        Price = Integer.parseInt( F_Price ) * count;
        tv_total.setText( "₹ "+ Price );

        tv_text_charge.setText( "₹ "+ Text_Charge );
        tv_delivery_charge.setText( "₹ "+ Delivery_Charge );

        Total_Price = Price + Text_Charge + Delivery_Charge;
        tv_total_price.setText( "₹ "+Total_Price );
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );

        builder.setTitle( "Payment ID" );

        builder.setMessage( s );

        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

//        Toast.makeText( this, s, Toast.LENGTH_SHORT ).show();
    }
}