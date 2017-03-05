package com.zomatosampleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 5/3/16.
 */
public class Popularity extends SugarRecord{

    @SerializedName("popularity")
    @Expose
    private String popularity;
    @SerializedName("nightlife_index")
    @Expose
    private String nightlifeIndex;
    @SerializedName("top_cuisines")
    @Expose
    private List<String> topCuisines = new ArrayList<>();

    public Popularity(){}

    public Popularity(String popularity, String nightlifeIndex, List<String> topCuisines) {
        this.popularity = popularity;
        this.nightlifeIndex = nightlifeIndex;
        this.topCuisines = topCuisines;
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
     * The nightlifeIndex
     */
    public String getNightlifeIndex() {
        return nightlifeIndex;
    }

    /**
     *
     * @param nightlifeIndex
     * The nightlife_index
     */
    public void setNightlifeIndex(String nightlifeIndex) {
        this.nightlifeIndex = nightlifeIndex;
    }

    /**
     *
     * @return
     * The topCuisines
     */
    public List<String> getTopCuisines() {
        return topCuisines;
    }

    /**
     *
     * @param topCuisines
     * The top_cuisines
     */
    public void setTopCuisines(List<String> topCuisines) {
        this.topCuisines = topCuisines;
    }
}
