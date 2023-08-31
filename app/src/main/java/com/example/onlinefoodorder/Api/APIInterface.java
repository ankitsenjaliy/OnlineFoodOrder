package com.example.onlinefoodorder.Api;

import com.example.onlinefoodorder.Model.AddCartModel;
import com.example.onlinefoodorder.Model.AllDataCustomer;
import com.example.onlinefoodorder.Model.AllFood1;
import com.example.onlinefoodorder.Model.DeleteCartFood;
import com.example.onlinefoodorder.Model.ForgotPasswordModel;
import com.example.onlinefoodorder.Model.PopularAllFood1;
import com.example.onlinefoodorder.Model.PopularFoodModel1;
import com.example.onlinefoodorder.Model.HotelModel1;
import com.example.onlinefoodorder.Model.RatingApplicationModel;
import com.example.onlinefoodorder.Model.LoginModel;
import com.example.onlinefoodorder.Model.RegistrationModel;
import com.example.onlinefoodorder.Model.ShowCartModel1;
import com.example.onlinefoodorder.Model.UpdateProfileModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("apiRegistration.php")
    Call<RegistrationModel> register(@Field("Customer_Name") String Customer_Name, @Field("Customer_Phone_No") String Customer_Phone_No, @Field("Customer_Email_Id") String Customer_Email_Id, @Field("Customer_Password") String Customer_Password, @Field("Customer_Address") String Customer_Address);

    @FormUrlEncoded
    @POST("apiLogin.php")
    Call<LoginModel> login(@Field( "Customer_Id" ) int Customer_Id, @Field( "Customer_Phone_No" ) String Customer_Phone_No, @Field( "Customer_Password" ) String Customer_Password);

    @FormUrlEncoded
    @POST("apiForgotPassword.php")
    Call<ForgotPasswordModel> forgot_password(@Field( "Customer_Email_Id" ) String Customer_Email_Id);

    @POST("apiPopularFood.php")
    Call<PopularFoodModel1> popularFood();

    @POST("apiHotels.php")
    Call<HotelModel1> restaurant();

    @FormUrlEncoded
    @POST("apiCustomer.php")
    Call<AllDataCustomer> customer(@Field( "Customer_Id" ) int Customer_Id);

    @FormUrlEncoded
    @POST("apiProfileUpdate.php")
    Call<UpdateProfileModel> update_profile(@Field( "Customer_Name" ) String Customer_Name, @Field( "Customer_Phone_No" ) String Customer_Phone_No, @Field( "Customer_Email_Id" ) String Customer_Email_Id, @Field( "Customer_Address" ) String Customer_Address, @Field( "Customer_Id" ) int Customer_Id);

    @FormUrlEncoded
    @POST("apiRatingApplication.php")
    Call<RatingApplicationModel> rating_application(@Field( "Rating_No" ) String Rating_No, @Field( "Rating_Description" ) String Rating_Description);

    @FormUrlEncoded
    @POST("apiAllFoods.php")
    Call<AllFood1> all_foods(@Field("Hotel_Name") String Hotel_Name);

    @FormUrlEncoded
    @POST("apiPopularAllFoods.php")
    Call<PopularAllFood1> popular_all_foods(@Field( "Item_Food_Name" ) String Item_Food_Name);

    @FormUrlEncoded
    @POST("apiAddCartFood.php")
    Call<AddCartModel> add_cart(@Field( "Food_Id" ) int Food_Id, @Field( "Customer_Id" ) int Customer_Id, @Field( "Hotel_Name" ) String Hotel_Name, @Field( "Food_Name" ) String Food_Name, @Field( "Food_Price" ) String Food_Price, @Field( "Food_Description" ) String Food_Description, @Field( "Food_Image" ) String Food_Image);

    @FormUrlEncoded
    @POST("apiShowCartFood.php")
    Call<ShowCartModel1> show_cart(@Field( "Customer_Id" ) int Customer_Id);

    @FormUrlEncoded
    @POST("apiDeleteFoodCart.php")
    Call<DeleteCartFood> delete_cart_food(@Field( "Food_Id" ) String Food_Id);
}
