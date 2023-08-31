package com.example.onlinefoodorder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCustomerData {

    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("Customer_Phone_No")
    @Expose
    private String customerPhoneNo;
    @SerializedName("Customer_Email_Id")
    @Expose
    private String customerEmailId;
    @SerializedName("Customer_Password")
    @Expose
    private String customerPassword;
    @SerializedName("Customer_Address")
    @Expose
    private String customerAddress;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

}
