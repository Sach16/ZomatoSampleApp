package com.zomatosampleapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Ramesh on 5/3/16.
 */
public class Photo extends SugarRecord{
//    @SerializedName("id")
//    @Expose
//    private String id_;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("res_id")
    @Expose
    private String resId;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("friendly_time")
    @Expose
    private String friendlyTime;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("comments_count")
    @Expose
    private String commentsCount;
    @SerializedName("likes_count")
    @Expose
    private String likesCount;

    public Photo(){}

    public Photo(String id_, String url, String thumbUrl, User user, String resId, String caption, String timestamp, String friendlyTime, String width, String height, String commentsCount, String likesCount) {
//        this.id_ = id_;
        this.url = url;
        this.thumbUrl = thumbUrl;
        this.user = user;
        this.resId = resId;
        this.caption = caption;
        this.timestamp = timestamp;
        this.friendlyTime = friendlyTime;
        this.width = width;
        this.height = height;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
    }

    /**
     *
     * @return
     * The id
     */
//    public String getId_() {
//        return id_;
//    }

    /**
     *
     * @param id
     * The id
     */
//    public void setId(String id) {
//        this.id_ = id;
//    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The thumbUrl
     */
    public String getThumbUrl() {
        return thumbUrl;
    }

    /**
     *
     * @param thumbUrl
     * The thumb_url
     */
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The resId
     */
    public String getResId() {
        return resId;
    }

    /**
     *
     * @param resId
     * The res_id
     */
    public void setResId(String resId) {
        this.resId = resId;
    }

    /**
     *
     * @return
     * The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     * The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     * The friendlyTime
     */
    public String getFriendlyTime() {
        return friendlyTime;
    }

    /**
     *
     * @param friendlyTime
     * The friendly_time
     */
    public void setFriendlyTime(String friendlyTime) {
        this.friendlyTime = friendlyTime;
    }

    /**
     *
     * @return
     * The width
     */
    public String getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The commentsCount
     */
    public String getCommentsCount() {
        return commentsCount;
    }

    /**
     *
     * @param commentsCount
     * The comments_count
     */
    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    /**
     *
     * @return
     * The likesCount
     */
    public String getLikesCount() {
        return likesCount;
    }

    /**
     *
     * @param likesCount
     * The likes_count
     */
    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }
}
