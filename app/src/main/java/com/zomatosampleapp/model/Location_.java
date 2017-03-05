package com.zomatosampleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Ramesh on 5/3/16.
 */
public class Location_ extends SugarRecord{
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("country_id")
    @Expose
    private String countryId;


    public Location_(){}

    public Location_(String locality, String city, String latitude, String longitude, String zipcode, String countryId, String address) {
        this.locality = locality;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipcode = zipcode;
        this.countryId = countryId;
        this.address = address;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     *
     * @param locality
     * The locality
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
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
     * The zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     * The zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
}
