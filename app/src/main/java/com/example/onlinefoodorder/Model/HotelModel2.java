package com.example.onlinefoodorder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelModel2 {

    @SerializedName("Hotel_Id")
    @Expose
    private String hotelId;
    @SerializedName("Hotel_Name")
    @Expose
    private String hotelName;
    @SerializedName("Hotel_Discount")
    @Expose
    private String hotelDiscount;
    @SerializedName("Hotel_Image")
    @Expose
    private String hotelImage;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDiscount() {
        return hotelDiscount;
    }

    public void setHotelDiscount(String hotelDiscount) {
        this.hotelDiscount = hotelDiscount;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }
}