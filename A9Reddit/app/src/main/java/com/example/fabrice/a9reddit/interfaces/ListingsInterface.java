package com.example.fabrice.a9reddit.interfaces;


import com.example.fabrice.a9reddit.models.Listing;

import java.lang.String;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ListingsInterface {

    /**
     * Gaat de JSON informatie ophalen voor een bepaalde subreddit en deze parsen.
     * @param section In dit geval altijd /r/
     * @param subreddit De naam van de subdreddit die opgehaald moet worden
     * @param data De callback interface die opgeroepen moet worden na het laden.
     */
    @GET("/{section}/{subreddit}")
    void getListing(@Path("section") String section, @Path("subreddit") String subreddit, Callback<Listing> data );

}
