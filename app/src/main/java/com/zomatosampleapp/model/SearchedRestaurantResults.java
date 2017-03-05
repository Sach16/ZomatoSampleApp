package com.zomatosampleapp.model;

/**
 * Created by Ramesh on 3/8/16.
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class SearchedRestaurantResults extends SugarRecord{

    @SerializedName("results_found")
    @Expose
    private String resultsFound;
    @SerializedName("results_start")
    @Expose
    private String resultsStart;
    @SerializedName("results_shown")
    @Expose
    private String resultsShown;
    @SerializedName("restaurants")
    @Expose
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    public SearchedRestaurantResults(){}

    public SearchedRestaurantResults(String resultsFound, List<Restaurant> restaurants, String resultsShown, String resultsStart) {
        this.resultsFound = resultsFound;
        this.restaurants = restaurants;
        this.resultsShown = resultsShown;
        this.resultsStart = resultsStart;
    }

    /**
     * @return The resultsFound
     */
    public String getResultsFound() {
        return resultsFound;
    }

    /**
     * @param resultsFound The results_found
     */
    public void setResultsFound(String resultsFound) {
        this.resultsFound = resultsFound;
    }

    /**
     * @return The resultsStart
     */
    public String getResultsStart() {
        return resultsStart;
    }

    /**
     * @param resultsStart The results_start
     */
    public void setResultsStart(String resultsStart) {
        this.resultsStart = resultsStart;
    }

    /**
     * @return The resultsShown
     */
    public String getResultsShown() {
        return resultsShown;
    }

    /**
     * @param resultsShown The results_shown
     */
    public void setResultsShown(String resultsShown) {
        this.resultsShown = resultsShown;
    }

    /**
     * @return The restaurants
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * @param restaurants The restaurants
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}