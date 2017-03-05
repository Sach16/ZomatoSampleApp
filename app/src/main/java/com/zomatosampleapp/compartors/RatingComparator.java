package com.zomatosampleapp.compartors;

import com.zomatosampleapp.model.BestRatedRestaurant;

import java.util.Comparator;

/**
 * Created by Ramesh on 5/3/16.
 */
public class RatingComparator implements Comparator<BestRatedRestaurant> {
    @Override
    public int compare(BestRatedRestaurant rest1, BestRatedRestaurant rest2) {
        return rest2.getRestaurant().getUserRating().getAggregateRating().compareTo(rest1.getRestaurant().getUserRating().getAggregateRating());
    }
}