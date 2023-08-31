package com.example.onlinefoodorder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowCartModel2 {

    @SerializedName("Food_Id")
    @Expose
    private String foodId;
    @SerializedName("Hotel_Name")
    @Expose
    private String hotelName;
    @SerializedName("Food_Name")
    @Expose
    private String foodName;
    @SerializedName("Food_Price")
    @Expose
    private String foodPrice;
    @SerializedName("Food_Description")
    @Expose
    private String foodDescription;
    @SerializedName("Food_Image")
    @Expose
    private String foodImage;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

}
