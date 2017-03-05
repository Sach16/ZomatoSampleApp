package com.zomatosampleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Ramesh on 5/3/16.
 */
public class LocationSuggestion extends SugarRecord{

    @SerializedName("entity_type")
    @Expose
    private String entityType;
    @SerializedName("entity_id")
    @Expose
    private Integer entityId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;

    public LocationSuggestion(){}

    public LocationSuggestion(String entityType, Integer entityId, String title, Double latitude, Double longitude, Integer cityId, String cityName, Integer countryId, String countryName) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityId = cityId;
        this.cityName = cityName;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     *
     * @return
     * The entityType
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     *
     * @param entityType
     * The entity_type
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    /**
     *
     * @return
     * The entityId
     */
    public Integer getEntityId() {
        return entityId;
    }

    /**
     *
     * @param entityId
     * The entity_id
     */
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     *
     * @param cityId
     * The city_id
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     *
     * @return
     * The cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * @param cityName
     * The city_name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     *
     * @return
     * The countryId
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId
     * The country_id
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     *
     * @return
     * The countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @param countryName
     * The country_name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
