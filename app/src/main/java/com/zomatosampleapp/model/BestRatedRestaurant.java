package com.zomatosampleapp.model;

/**
 * Created by Ramesh on 5/3/16.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class BestRatedRestaurant extends SugarRecord{


    //    @SerializedName("id")
//    @Expose
//    private String id_;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location_ location;
    @SerializedName("average_cost_for_two")
    @Expose
    private String averageCostForTwo;
    @SerializedName("price_range")
    @Expose
    private String priceRange;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("photos_url")
    @Expose
    private String photosUrl;
    @SerializedName("menu_url")
    @Expose
    private String menuUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;
    @SerializedName("has_online_delivery")
    @Expose
    private String hasOnlineDelivery;
    @SerializedName("is_delivering_now")
    @Expose
    private String isDeliveringNow;
    @SerializedName("deeplink")
    @Expose
    private String deeplink;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("all_reviews_count")
    @Expose
    private String allReviewsCount;
    @SerializedName("photo_count")
    @Expose
    private String photoCount;
    @SerializedName("phone_numbers")
    @Expose
    private String phoneNumbers;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("all_reviews")
    @Expose
    private List<AllReview> allReviews = new ArrayList<AllReview>();
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;


    public BestRatedRestaurant(){}

    public BestRatedRestaurant(String name, String url, Location_ location, String averageCostForTwo, String priceRange, String currency, String thumb, String featuredImage, String photosUrl, String menuUrl, String eventsUrl, UserRating userRating, String hasOnlineDelivery, String isDeliveringNow, String deeplink, String cuisines, String allReviewsCount, String photoCount, String phoneNumbers, List<Photo> photos, List<AllReview> allReviews, Restaurant restaurant) {
//        this.id_ = id;
        this.name = name;
        this.url = url;
        this.location = location;
        this.averageCostForTwo = averageCostForTwo;
        this.priceRange = priceRange;
        this.currency = currency;
        this.thumb = thumb;
        this.featuredImage = featuredImage;
        this.photosUrl = photosUrl;
        this.menuUrl = menuUrl;
        this.eventsUrl = eventsUrl;
        this.userRating = userRating;
        this.hasOnlineDelivery = hasOnlineDelivery;
        this.isDeliveringNow = isDeliveringNow;
        this.deeplink = deeplink;
        this.cuisines = cuisines;
        this.allReviewsCount = allReviewsCount;
        this.photoCount = photoCount;
        this.phoneNumbers = phoneNumbers;
        this.photos = photos;
        this.allReviews = allReviews;
        this.restaurant = restaurant;

    }

    /**
     *
     * @return
     * The restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     *
     * @param restaurant
     * The restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
     * The location
     */
    public Location_ getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location_ location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The averageCostForTwo
     */
    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    /**
     *
     * @param averageCostForTwo
     * The average_cost_for_two
     */
    public void setAverageCostForTwo(String averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    /**
     *
     * @return
     * The priceRange
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     *
     * @param priceRange
     * The price_range
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb
     * The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return
     * The featuredImage
     */
    public String getFeaturedImage() {
        return featuredImage;
    }

    /**
     *
     * @param featuredImage
     * The featured_image
     */
    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    /**
     *
     * @return
     * The photosUrl
     */
    public String getPhotosUrl() {
        return photosUrl;
    }

    /**
     *
     * @param photosUrl
     * The photos_url
     */
    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    /**
     *
     * @return
     * The menuUrl
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     *
     * @param menuUrl
     * The menu_url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     *
     * @return
     * The eventsUrl
     */
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     *
     * @param eventsUrl
     * The events_url
     */
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    /**
     *
     * @return
     * The userRating
     */
    public UserRating getUserRating() {
        return userRating;
    }

    /**
     *
     * @param userRating
     * The user_rating
     */
    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    /**
     *
     * @return
     * The hasOnlineDelivery
     */
    public String getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    /**
     *
     * @param hasOnlineDelivery
     * The has_online_delivery
     */
    public void setHasOnlineDelivery(String hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    /**
     *
     * @return
     * The isDeliveringNow
     */
    public String getIsDeliveringNow() {
        return isDeliveringNow;
    }

    /**
     *
     * @param isDeliveringNow
     * The is_delivering_now
     */
    public void setIsDeliveringNow(String isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    /**
     *
     * @return
     * The deeplink
     */
    public String getDeeplink() {
        return deeplink;
    }

    /**
     *
     * @param deeplink
     * The deeplink
     */
    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    /**
     *
     * @return
     * The cuisines
     */
    public String getCuisines() {
        return cuisines;
    }

    /**
     *
     * @param cuisines
     * The cuisines
     */
    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    /**
     *
     * @return
     * The allReviewsCount
     */
    public String getAllReviewsCount() {
        return allReviewsCount;
    }

    /**
     *
     * @param allReviewsCount
     * The all_reviews_count
     */
    public void setAllReviewsCount(String allReviewsCount) {
        this.allReviewsCount = allReviewsCount;
    }

    /**
     *
     * @return
     * The photoCount
     */
    public String getPhotoCount() {
        return photoCount;
    }

    /**
     *
     * @param photoCount
     * The photo_count
     */
    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
    }

    /**
     *
     * @return
     * The phoneNumbers
     */
    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     *
     * @param phoneNumbers
     * The phone_numbers
     */
    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     *
     * @return
     * The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     * The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     *
     * @return
     * The allReviews
     */
    public List<AllReview> getAllReviews() {
        return allReviews;
    }

    /**
     *
     * @param allReviews
     * The all_reviews
     */
    public void setAllReviews(List<AllReview> allReviews) {
        this.allReviews = allReviews;
    }
}
