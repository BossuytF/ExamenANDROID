package com.example.fabrice.a9reddit.models;

import com.google.gson.annotations.SerializedName;

public class ListingElement {

    @SerializedName("data")
    private ListingData listingData;

    public ListingData getListingData() {
        return listingData;
    }

    public void setListingData(ListingData listingData) {
        this.listingData = listingData;
    }
}
