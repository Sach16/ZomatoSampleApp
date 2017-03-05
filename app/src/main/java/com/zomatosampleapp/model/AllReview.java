package com.zomatosampleapp.model;

/**
 * Created by Ramesh on 5/3/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class AllReview extends SugarRecord{

    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("review_text")
    @Expose
    private String reviewText;
    @SerializedName("rating_color")
    @Expose
    private String ratingColor;
    @SerializedName("review_time_friendly")
    @Expose
    private String reviewTimeFriendly;
    @SerializedName("rating_text")
    @Expose
    private String ratingText;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("comments_count")
    @Expose
    private String commentsCount;

    public AllReview(){
    }

    public AllReview(String rating, String reviewText, String id, String ratingColor, String reviewTimeFriendly, String ratingText, String timestamp, String likes, User user, String commentsCount) {
        this.rating = rating;
        this.reviewText = reviewText;
//        this.id_ = id;
        this.ratingColor = ratingColor;
        this.reviewTimeFriendly = reviewTimeFriendly;
        this.ratingText = ratingText;
        this.timestamp = timestamp;
        this.likes = likes;
        this.user = user;
        this.commentsCount = commentsCount;
    }

    /**
     *
     * @return
     * The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The reviewText
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     *
     * @param reviewText
     * The review_text
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
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
     * The ratingColor
     */
    public String getRatingColor() {
        return ratingColor;
    }

    /**
     *
     * @param ratingColor
     * The rating_color
     */
    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    /**
     *
     * @return
     * The reviewTimeFriendly
     */
    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    /**
     *
     * @param reviewTimeFriendly
     * The review_time_friendly
     */
    public void setReviewTimeFriendly(String reviewTimeFriendly) {
        this.reviewTimeFriendly = reviewTimeFriendly;
    }

    /**
     *
     * @return
     * The ratingText
     */
    public String getRatingText() {
        return ratingText;
    }

    /**
     *
     * @param ratingText
     * The rating_text
     */
    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
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
     * The likes
     */
    public String getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     * The likes
     */
    public void setLikes(String likes) {
        this.likes = likes;
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

}