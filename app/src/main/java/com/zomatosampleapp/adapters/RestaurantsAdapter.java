package com.zomatosampleapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Bind;

import com.zomatosampleapp.R;
import com.zomatosampleapp.model.Restaurant;


/**
 * Created by Ramesh on 5/3/16.
 */
public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>{
    private final Activity mContext;
    private final LayoutInflater mInflater;
    private List<Restaurant> mRestaurantsList;

    public RestaurantsAdapter(Activity context, List<Restaurant> restaurantsList) {
        this.mRestaurantsList = new ArrayList<>(restaurantsList);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mInflater.inflate(R.layout.restaurant_details_layout, parent, false);
        return new RestaurantsViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RestaurantsViewHolder holder, int position) {

        final Restaurant restaurant = mRestaurantsList.get(position);

        if (restaurant.getFeaturedImage() != null && !restaurant.getFeaturedImage().isEmpty()){
            Picasso.with(mContext).load(restaurant.getFeaturedImage()).placeholder(R.drawable.placeholder_rev).into(holder.restaurantImage);
        }

        holder.restaurantName.setText(restaurant.getName());
        holder.restaurantLocation.setText(restaurant.getRestaurantLocation().split(",")[2]);
        holder.restaurantPrice.setText("Avg cost for 2 : Rs " + restaurant.getAverageCostForTwo() + "/-");
        holder.restaurantRating.setRating(Float.parseFloat(restaurant.getUserRatingDetails().split(",")[0]));
        holder.topCuisines.setText("Top cuisines : " + restaurant.getCuisines());
        holder.votes.setText(restaurant.getUserRatingDetails().split(",")[2] + " votes");
        holder.votes.setTextColor(Color.parseColor("#" + restaurant.getUserRatingDetails().split(",")[1]));
        holder.getDirections.setTag(restaurant.getRestaurantLocation().split(",")[0]+":" + restaurant.getRestaurantLocation().split(",")[1]);

        holder.getDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String lat_lon = view.getTag().toString();
                    if (lat_lon != null) {
                        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f", Double.parseDouble(lat_lon.split(":")[0]), Double.parseDouble(lat_lon.split(":")[1]));
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                        mContext.startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d("RestaurantsAdapter", "Google maps app not found");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurantsList.size();
    }


    class RestaurantsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.restaurant_image)
        ImageView restaurantImage;
        @Bind(R.id.restaurant_name)
        TextView restaurantName;
        @Bind(R.id.restaurant_location)
        TextView restaurantLocation;
        @Bind(R.id.restaurant_price)
        TextView restaurantPrice;
        @Bind(R.id.restaurant_rating)
        RatingBar restaurantRating;
        @Bind(R.id.restaurant_topcuisines)
        TextView topCuisines;
        @Bind(R.id.restaurant_votes)
        TextView votes;
        @Bind(R.id.get_directions)
        Button getDirections;


        public RestaurantsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public void animateTo(List<Restaurant> restaurants) {
        if(restaurants.size() >1) {
            applyAndAnimateRemovals(restaurants);
            applyAndAnimateAdditions(restaurants);
            applyAndAnimateMovedItems(restaurants);
        }
    }

    private void applyAndAnimateRemovals(List<Restaurant> newRestaurants) {
        for (int i = mRestaurantsList.size() - 1; i >= 0; i--) {
            final Restaurant restaurant = mRestaurantsList.get(i);
            if (!newRestaurants.contains(restaurant)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Restaurant> newRestaurants) {
        for (int i = 0, count = newRestaurants.size(); i < count; i++) {
            final Restaurant restaurant = newRestaurants.get(i);
            if (!mRestaurantsList.contains(restaurant)) {
                addItem(i, restaurant);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Restaurant> newRestaurants) {
        for (int toPosition = newRestaurants.size() - 1; toPosition >= 0; toPosition--) {
            final Restaurant restaurant = newRestaurants.get(toPosition);
            final int fromPosition = mRestaurantsList.indexOf(restaurant);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Restaurant removeItem(int position) {
        final Restaurant restaurant = mRestaurantsList.remove(position);
        notifyItemRemoved(position);
        return restaurant;
    }

    public void addItem(int position, Restaurant restaurant) {
        mRestaurantsList.add(position, restaurant);
        notifyItemInserted(position);
    }


    public void moveItem(int fromPosition, int toPosition) {
        final Restaurant restaurant = mRestaurantsList.remove(fromPosition);
        mRestaurantsList.add(toPosition, restaurant);
        notifyItemMoved(fromPosition, toPosition);
    }


    public void updateData(List<Restaurant> restaurantArrayList) {
        mRestaurantsList.clear();
        mRestaurantsList.addAll(restaurantArrayList);
        notifyDataSetChanged();
    }
}
