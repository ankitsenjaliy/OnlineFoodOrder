package com.example.onlinefoodorder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularFoodModel2 {

    @SerializedName("Item_Food_Id")
    @Expose
    private String itemFoodId;
    @SerializedName("Item_Food_Name")
    @Expose
    private String itemFoodName;
    @SerializedName("Item_Food_Image")
    @Expose
    private String itemFoodImage;

    public String getItemFoodId() {
        return itemFoodId;
    }

    public void setItemFoodId(String itemFoodId) {
        this.itemFoodId = itemFoodId;
    }

    public String getItemFoodName() {
        return itemFoodName;
    }

    public void setItemFoodName(String itemFoodName) {
        this.itemFoodName = itemFoodName;
    }

    public String getItemFoodImage() {
        return itemFoodImage;
    }

    public void setItemFoodImage(String itemFoodImage) {
        this.itemFoodImage = itemFoodImage;
    }

}
