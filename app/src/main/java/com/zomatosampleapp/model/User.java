package com.zomatosampleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Ramesh on 5/3/16.
 */
public class User extends SugarRecord{
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("zomato_handle")
    @Expose
    private String zomatoHandle;
    @SerializedName("foodie_level")
    @Expose
    private String foodieLevel;
    @SerializedName("foodie_level_num")
    @Expose
    private String foodieLevelNum;
    @SerializedName("foodie_color")
    @Expose
    private String foodieColor;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;
    @SerializedName("profile_deeplink")
    @Expose
    private String profileDeeplink;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    public User(){}

    public User(String name, String zomatoHandle, String foodieLevel, String foodieLevelNum, String foodieColor, String profileUrl, String profileDeeplink, String profileImage) {
        this.name = name;
        this.zomatoHandle = zomatoHandle;
        this.foodieLevel = foodieLevel;
        this.foodieLevelNum = foodieLevelNum;
        this.foodieColor = foodieColor;
        this.profileUrl = profileUrl;
        this.profileDeeplink = profileDeeplink;
        this.profileImage = profileImage;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The zomatoHandle
     */
    public String getZomatoHandle() {
        return zomatoHandle;
    }

    /**
     *
     * @param zomatoHandle
     * The zomato_handle
     */
    public void setZomatoHandle(String zomatoHandle) {
        this.zomatoHandle = zomatoHandle;
    }

    /**
     *
     * @return
     * The foodieLevel
     */
    public String getFoodieLevel() {
        return foodieLevel;
    }

    /**
     *
     * @param foodieLevel
     * The foodie_level
     */
    public void setFoodieLevel(String foodieLevel) {
        this.foodieLevel = foodieLevel;
    }

    /**
     *
     * @return
     * The foodieLevelNum
     */
    public String getFoodieLevelNum() {
        return foodieLevelNum;
    }

    /**
     *
     * @param foodieLevelNum
     * The foodie_level_num
     */
    public void setFoodieLevelNum(String foodieLevelNum) {
        this.foodieLevelNum = foodieLevelNum;
    }

    /**
     *
     * @return
     * The foodieColor
     */
    public String getFoodieColor() {
        return foodieColor;
    }

    /**
     *
     * @param foodieColor
     * The foodie_color
     */
    public void setFoodieColor(String foodieColor) {
        this.foodieColor = foodieColor;
    }

    /**
     *
     * @return
     * The profileUrl
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     *
     * @param profileUrl
     * The profile_url
     */
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     *
     * @return
     * The profileDeeplink
     */
    public String getProfileDeeplink() {
        return profileDeeplink;
    }

    /**
     *
     * @param profileDeeplink
     * The profile_deeplink
     */
    public void setProfileDeeplink(String profileDeeplink) {
        this.profileDeeplink = profileDeeplink;
    }

    /**
     *
     * @return
     * The profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     *
     * @param profileImage
     * The profile_image
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
