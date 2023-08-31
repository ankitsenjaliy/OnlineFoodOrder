package com.example.onlinefoodorder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularAllFood2 {

    @SerializedName("Food_Id")
    @Expose
    private String foodId;
    @SerializedName("Item_Food_Id")
    @Expose
    private String itemFoodId;
    @SerializedName("Hotel_Name")
    @Expose
    private String hotelName;
    @SerializedName("Food_Name")
    @Expose
    private String foodName;
    @SerializedName("Food_Description")
    @Expose
    private String foodDescription;
    @SerializedName("Food_Price")
    @Expose
    private String foodPrice;
    @SerializedName("Food_Image")
    @Expose
    private String foodImage;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getItemFoodId() {
        return itemFoodId;
    }

    public void setItemFoodId(String itemFoodId) {
        this.itemFoodId = itemFoodId;
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

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
