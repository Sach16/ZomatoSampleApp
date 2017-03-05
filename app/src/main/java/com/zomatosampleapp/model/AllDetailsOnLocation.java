package com.zomatosampleapp.model;

/**
 * Created by Ramesh on 5/3/16.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class AllDetailsOnLocation extends SugarRecord {

    @SerializedName("popularity")
    @Expose
    private String popularity;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("best_rated_restaurants")
    @Expose
    private List<BestRatedRestaurant> bestRatedRestaurants = new ArrayList<BestRatedRestaurant>();
    @SerializedName("nightlife_index")
    @Expose
    private String nightlifeIndex;
    @SerializedName("nearby_res")
    @Expose
    private List<String> nearbyRes = new ArrayList<String>();
    @SerializedName("top_cuisines")
    @Expose
    private List<String> topCuisines = new ArrayList<String>();
    @SerializedName("popularity_res")
    @Expose
    private String popularityRes;
    @SerializedName("nightlife_res")
    @Expose
    private String nightlifeRes;
    @SerializedName("subzone")
    @Expose
    private String subzone;
    @SerializedName("subzone_id")
    @Expose
    private Integer subzoneId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("num_restaurant")
    @Expose
    private Integer numRestaurant;
    @SerializedName("best_rated_restaurant")
    @Expose
    private List<BestRatedRestaurant> bestRatedRestaurant = new ArrayList<BestRatedRestaurant>();

    public AllDetailsOnLocation(){
    }

    public AllDetailsOnLocation(String popularity, Location location, List<BestRatedRestaurant> bestRatedRestaurants, String nightlifeIndex, List<String> nearbyRes, List<String> topCuisines, String popularityRes, String nightlifeRes, String subzone, Integer subzoneId, String city, Integer numRestaurant, List<BestRatedRestaurant> bestRatedRestaurant) {
        this.popularity = popularity;
        this.location = location;
        this.bestRatedRestaurants = bestRatedRestaurants;
        this.nightlifeIndex = nightlifeIndex;
        this.nearbyRes = nearbyRes;
        this.topCuisines = topCuisines;
        this.popularityRes = popularityRes;
        this.nightlifeRes = nightlifeRes;
        this.subzone = subzone;
        this.subzoneId = subzoneId;
        this.city = city;
        this.numRestaurant = numRestaurant;
        this.bestRatedRestaurant = bestRatedRestaurant;
    }

    /**
     *
     * @return
     * The popularity
     */
    public String getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     * The popularity
     */
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The bestRatedRestaurants
     */
    public List<BestRatedRestaurant> getBestRatedRestaurants() {
        return bestRatedRestaurants;
    }

    /**
     *
     * @param bestRatedRestaurants
     * The best_rated_restaurants
     */
    public void setBestRatedRestaurants(List<BestRatedRestaurant> bestRatedRestaurants) {
        this.bestRatedRestaurants = bestRatedRestaurants;
    }


    public String getNightlifeIndex() {
        return nightlifeIndex;
    }

    public void setNightlifeIndex(String nightlifeIndex) {
        this.nightlifeIndex = nightlifeIndex;
    }

    public List<String> getNearbyRes() {
        return nearbyRes;
    }

    public void setNearbyRes(List<String> nearbyRes) {
        this.nearbyRes = nearbyRes;
    }

    public List<String> getTopCuisines() {
        return topCuisines;
    }

    public void setTopCuisines(List<String> topCuisines) {
        this.topCuisines = topCuisines;
    }

    public String getPopularityRes() {
        return popularityRes;
    }

    public void setPopularityRes(String popularityRes) {
        this.popularityRes = popularityRes;
    }

    public String getNightlifeRes() {
        return nightlifeRes;
    }

    public void setNightlifeRes(String nightlifeRes) {
        this.nightlifeRes = nightlifeRes;
    }

    public String getSubzone() {
        return subzone;
    }

    public void setSubzone(String subzone) {
        this.subzone = subzone;
    }

    public Integer getSubzoneId() {
        return subzoneId;
    }

    public void setSubzoneId(Integer subzoneId) {
        this.subzoneId = subzoneId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumRestaurant() {
        return numRestaurant;
    }

    public void setNumRestaurant(Integer numRestaurant) {
        this.numRestaurant = numRestaurant;
    }

    public List<BestRatedRestaurant> getBestRatedRestaurant() {
        return bestRatedRestaurant;
    }

    public void setBestRatedRestaurant(List<BestRatedRestaurant> bestRatedRestaurant) {
        this.bestRatedRestaurant = bestRatedRestaurant;
    }

}
