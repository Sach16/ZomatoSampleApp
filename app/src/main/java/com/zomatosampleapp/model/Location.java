package com.zomatosampleapp.model;

/**
 * Created by Ramesh on 5/3/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Location extends SugarRecord{

    @SerializedName("entity_type")
    @Expose
    private String entityType;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;

    public Location(){}

    public Location(String entityType, String entityId, String title, String latitude, String longitude, String cityId, String cityName, String countryId, String countryName) {
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
    public String getEntityId() {
        return entityId;
    }

    /**
     *
     * @param entityId
     * The entity_id
     */
    public void setEntityId(String entityId) {
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
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     *
     * @param cityId
     * The city_id
     */
    public void setCityId(String cityId) {
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
    public String getCountryId() {
        return countryId;
    }

    /**
     *
     * @param countryId
     * The country_id
     */
    public void setCountryId(String countryId) {
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
