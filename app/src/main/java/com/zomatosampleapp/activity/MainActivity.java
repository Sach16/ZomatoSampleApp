package com.zomatosampleapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.orm.query.Select;
import com.zomatosampleapp.R;
import com.zomatosampleapp.adapters.RestaurantsAdapter;
import com.zomatosampleapp.compartors.RatingComparator;
import com.zomatosampleapp.interfaces.ServerCallback;
import com.zomatosampleapp.model.AllDetailsOnLocation;
import com.zomatosampleapp.model.BestRatedRestaurant;
import com.zomatosampleapp.model.LocationResponse;
import com.zomatosampleapp.model.LocationSuggestion;
import com.zomatosampleapp.model.Restaurant;
import com.zomatosampleapp.model.SearchedRestaurantResults;
import com.zomatosampleapp.network.Constants;
import com.zomatosampleapp.network.RequestManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Bind;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;


public class MainActivity extends AppCompatActivity implements ServerCallback ,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    @Bind(R.id.recycler_view)
    RecyclerView listView;
    @Bind(R.id.snackBarLayout)
    View snackBarLayout;
    @Bind(R.id.city_name)
    EditText cityName;
    @Bind(R.id.progress_layout)
    RelativeLayout progressLayout;
    @Bind(R.id.city_address)
    TextView currentAddress;
    @Bind(R.id.search_layout)
    TextInputLayout searchLayout;
    @Bind(R.id.restaurant_search)
    EditText searchRestaurant;


    private LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private List<BestRatedRestaurant> bestRestaurantArrayList;
    private RestaurantsAdapter adapter;
    private List<Restaurant> copyRestaurantArrList;
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    private LocationSuggestion locationSuggestionObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        if(mLocationRequest == null) {
            mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setNumUpdates(1);
            mLocationRequest.setInterval(50000);
            mLocationRequest.setFastestInterval(50000);
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }

        requestLocationPermission();

        cityName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchRestaurant.setText("");
                    performSearch();
                    return true;
                }
                return false;
            }
        });


        searchRestaurant.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchForRestaurantName(searchRestaurant.getText().toString());
                    return true;
                }
                return false;
            }
        });


        showKeyboard(this, cityName, false);

    }

    /**
     * Method to get restaurants based on the city name entered in cityname edit text
     * or the location got from current user location
     */
    private void performSearch() {
        showKeyboard(this, cityName, false);
        progressLayout.setVisibility(View.VISIBLE);
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.QUERY, cityName.getText().toString());
        RequestManager.getInstance(this).placeRequest(Constants.LOCATIONS_API, LocationResponse.class, this, params, false);
    }


    /**
     * Method to alert the user if gps is not enabled and ask to enable
     */
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your device GPS seems to be disabled, do you want to enable it ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Requests the Location permission.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestLocationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission()) {
                requestPermission();
            }
        }else{
            startLocationUpdates();
        }

    }

    /**
     * Method to start the location updates
     */
    protected void startLocationUpdates() {
        if(mGoogleApiClient !=null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    /**
     *
     * Method to check whether the permission is granted or not
     */
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to request location permission
     */
    private void requestPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            Snackbar.make(snackBarLayout, "Location access is required to fetch best restaurants in your location.",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    PERMISSIONS_REQUEST_LOCATION);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                }
                return;
            }

        }

    }

    /**
     * @param latitude
     * @param longitude
     * Method to get the userLocation on the latitude and longitude
     */
    private void getUserLocation(double latitude, double longitude){
        try {
            Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geoCoder.getFromLocation(latitude, longitude, 1);
            String areaName = addresses.get(0).getAddressLine(1);
            String cityStateName = addresses.get(0).getAddressLine(2);
            String countryName = addresses.get(0).getCountryName();

            Log.d("LOG", "getUserLocation Called : City Name = "+areaName+", "+cityStateName+", "+countryName);

            cityName.setText(areaName+", "+cityStateName+", "+countryName);
            performSearch();
            showKeyboard(this, cityName, false);

        } catch (IOException e) {
            Log.d("MainActivity", "IO Exception");
        } catch (NullPointerException e) {
            Log.d("MainActivity", "Null pointer exception");
        }
    }

    @Override
    public void complete(int code) {

    }

    @Override
    public void onAPIResponse(Object response, String apiMethod) {

        if(apiMethod.equals(Constants.LOCATIONS_API)){
            LocationResponse locationDetails = (LocationResponse) response;
            LocationResponse lss = LocationResponse.first(LocationResponse.class);
            if(locationDetails.getLocationSuggestions().size() >0) {

                currentAddress.setText(locationDetails.getLocationSuggestions().get(0).getTitle() + ", " + locationDetails.getLocationSuggestions().get(0).getCountryName());

                LocationSuggestion.deleteAll(LocationSuggestion.class);

                saveLocationResponse(locationDetails.getLocationSuggestions().get(0));

                Select locationSuggestionFromDb = Select.from(LocationSuggestion.class);

                locationSuggestionObj = (LocationSuggestion) locationSuggestionFromDb.first();

                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.ENTITY_ID, "" + locationSuggestionObj.getEntityId());
                params.put(Constants.ENTITY_TYPE, locationSuggestionObj.getEntityType());
                RequestManager.getInstance(this).placeRequest(Constants.LOCATION_DETAILS, AllDetailsOnLocation.class, this, params, false);
            }else{
                Snackbar.make(snackBarLayout, "The location you typed is not found.", Snackbar.LENGTH_LONG).show();
            }
        }

        if(apiMethod.equals(Constants.LOCATION_DETAILS)){
            progressLayout.setVisibility(View.GONE);

            AllDetailsOnLocation details = (AllDetailsOnLocation) response;
            if(details.getBestRatedRestaurant().size() > 0) {
                searchLayout.setVisibility(View.VISIBLE);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                listView.setVisibility(View.VISIBLE);
                listView.setLayoutManager(llm);
                listView.setHasFixedSize(true);
                listView.setItemAnimator(new LandingAnimator());

                bestRestaurantArrayList = details.getBestRatedRestaurant();
                Collections.sort(bestRestaurantArrayList, new RatingComparator());

                saveAllDetailsOnLocation(details, bestRestaurantArrayList);


                currentAddress.setText("Night life index of : " + currentAddress.getText().toString() + " is " + details.getNightlifeIndex());

                copyRestaurantArrList = new ArrayList<Restaurant>();
                copyRestaurantArrList = Restaurant.listAll(Restaurant.class);

                adapter = new RestaurantsAdapter(this, copyRestaurantArrList);
                ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
                listView.setAdapter(scaleAdapter);
                adapter.notifyDataSetChanged();
                scaleAdapter.notifyDataSetChanged();
            }else{
                Snackbar.make(snackBarLayout, "No restaurants found near to your location!", Snackbar.LENGTH_LONG).show();

            }
        }

        if(apiMethod.equals(Constants.SEARCH_API)){
            progressLayout.setVisibility(View.GONE);

            SearchedRestaurantResults results = (SearchedRestaurantResults) response;
            SearchedRestaurantResults lS = SearchedRestaurantResults.first(SearchedRestaurantResults.class);
            if(results.getRestaurants().size() > 0) {

                searchLayout.setVisibility(View.VISIBLE);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                listView.setVisibility(View.VISIBLE);
                listView.setLayoutManager(llm);
                listView.setHasFixedSize(true);
                listView.setItemAnimator(new LandingAnimator());

                List<Restaurant> restaurantArrayList = results.getRestaurants();

                saveAllRestaurantsDetails(null,restaurantArrayList, true);


                ArrayList<Restaurant> searchRestaurantList = new ArrayList<Restaurant>();
                searchRestaurantList = (ArrayList<Restaurant>) Restaurant.listAll(Restaurant.class);

                adapter = new RestaurantsAdapter(this, searchRestaurantList);
                ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(adapter);
                listView.swapAdapter(scaleAdapter,true);
                adapter.notifyDataSetChanged();
                scaleAdapter.notifyDataSetChanged();
            }else{
                Snackbar.make(snackBarLayout, "No restaurants found.", Snackbar.LENGTH_LONG).show();

            }
        }



    }

    /**
     * @param details
     * @param restaurantArrayList
     * Method to save all details on location and best restaurant details in database
     */
    private void saveAllDetailsOnLocation(AllDetailsOnLocation details,List<BestRatedRestaurant> restaurantArrayList) {
        AllDetailsOnLocation.deleteAll(AllDetailsOnLocation.class);
        AllDetailsOnLocation allDetailsOnLocation = new AllDetailsOnLocation(details.getPopularity(), details.getLocation(), restaurantArrayList,details.getNightlifeIndex(), details.getNearbyRes(),details.getTopCuisines(),details.getPopularityRes(),details.getNightlifeRes(),details.getSubzone(),details.getSubzoneId(),details.getCity(),details.getNumRestaurant(),restaurantArrayList);
        allDetailsOnLocation.save();
        saveAllRestaurantsDetails(restaurantArrayList, null,false);
    }

    /**
     * @param bestRestaurantArrayList
     * @param searchRestaurants
     * @param isSearchResult
     * Method to save all restaurant details of search results or best restaurants based on isSearchResult in database
     */
    private void saveAllRestaurantsDetails(List<BestRatedRestaurant> bestRestaurantArrayList, List<Restaurant> searchRestaurants, boolean isSearchResult){
        Restaurant.deleteAll(Restaurant.class);
        if(isSearchResult){
            for (int i = 0; i < searchRestaurants.size(); i++) {
                Restaurant restaurant = searchRestaurants.get(i).getRestaurant();
                restaurant = new Restaurant(restaurant.getName(), restaurant.getUrl(),restaurant.getLocation(),restaurant.getCuisines(),restaurant.getAverageCostForTwo(), restaurant.getPriceRange(),restaurant.getCurrency(),restaurant.getOffers(),restaurant.getThumb(),restaurant.getUserRating(),restaurant.getPhotosUrl(),restaurant.getMenuUrl(),restaurant.getFeaturedImage(),restaurant.getHasOnlineDelivery(),restaurant.getIsDeliveringNow(),restaurant.getDeeplink(), "", "",restaurant.getEventsUrl(), restaurant.getLocation().getLatitude() + "," + restaurant.getLocation().getLongitude() +","+ restaurant.getLocation().getLocality(),restaurant.getUserRating().getAggregateRating() + "," + restaurant.getUserRating().getRatingColor() + "," + restaurant.getUserRating().getVotes());
                restaurant.save();
            }
        }else {
            for (int i = 0; i < bestRestaurantArrayList.size(); i++) {
                Restaurant restaurant = bestRestaurantArrayList.get(i).getRestaurant();
                restaurant = new Restaurant(restaurant.getName(), restaurant.getUrl(), restaurant.getLocation(), restaurant.getCuisines(), restaurant.getAverageCostForTwo(), restaurant.getPriceRange(), restaurant.getCurrency(), restaurant.getOffers(), restaurant.getThumb(), restaurant.getUserRating(), restaurant.getPhotosUrl(), restaurant.getMenuUrl(), restaurant.getFeaturedImage(), restaurant.getHasOnlineDelivery(), restaurant.getIsDeliveringNow(), restaurant.getDeeplink(), restaurant.getOrderUrl(), restaurant.getOrderDeeplink(), restaurant.getEventsUrl(), restaurant.getLocation().getLatitude() + "," + restaurant.getLocation().getLongitude() + "," + restaurant.getLocation().getLocality(), restaurant.getUserRating().getAggregateRating() + "," + restaurant.getUserRating().getRatingColor() + "," + restaurant.getUserRating().getVotes());
                restaurant.save();
            }
        }
    }


    /**
     * @param locationSuggestion
     * Method to save location response to database
     */
    private void saveLocationResponse(LocationSuggestion locationSuggestion) {
        if(locationSuggestion != null) {
            LocationSuggestion locationSuggestionRecord = new LocationSuggestion(locationSuggestion.getEntityType(), locationSuggestion.getEntityId(), locationSuggestion.getTitle(), locationSuggestion.getLatitude(), locationSuggestion.getLongitude(), locationSuggestion.getCityId(), locationSuggestion.getCityName(), locationSuggestion.getCountryId(), locationSuggestion.getCountryName());
            locationSuggestionRecord.save();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String apiMethod) {
        progressLayout.setVisibility(View.GONE);
        if(error instanceof NoConnectionError) {
            Snackbar.make(snackBarLayout, "No internet connection", Snackbar.LENGTH_LONG).show();
        } else if(error instanceof AuthFailureError){
            Snackbar.make(snackBarLayout, "Authentication failed.", Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.d("LOG", "onConnected Called " + mLastLocation.getLongitude() + " " + mLastLocation.getLatitude());
            getUserLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onPause() {
        if(mGoogleApiClient!=null && mGoogleApiClient.isConnected() ) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {
            Log.d("LOG", "onLocationChanged Called "+location.getLongitude()+" "+location.getLatitude());
            getUserLocation(location.getLatitude(), location.getLongitude());
        }
    }


    /**
     * @param query
     * Method to search for the restaurant name on the query
     */
    private void searchForRestaurantName(String query){
        showKeyboard(this, searchRestaurant, false);
        progressLayout.setVisibility(View.VISIBLE);

        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.SEARCH_QUERY, query);
        params.put(Constants.LATITUDE, ""+locationSuggestionObj.getLatitude());
        params.put(Constants.LONGITUDE,""+locationSuggestionObj.getLongitude());
        params.put(Constants.ENTITY_ID, ""+locationSuggestionObj.getEntityId());
        params.put(Constants.ENTITY_TYPE, locationSuggestionObj.getEntityType());
        RequestManager.getInstance(this).placeRequest(Constants.SEARCH_API, SearchedRestaurantResults.class, this, params, false);

    }

    /**
     * @param restaurants
     * @param query
     * @return List<BestRatedRestaurant>
     * Method to filtet the list with the query
     */
    private List<Restaurant> filter(List<Restaurant> restaurants, String query) {
        query = query.toLowerCase();

        final List<Restaurant> filteredModelList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            final String text = restaurant.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(restaurant);
            }
        }
        return filteredModelList;
    }

    /**
     * @param cntxt
     * @param text
     * @param show
     * Method to show of hide the keyboard
     */
    private void showKeyboard(Context cntxt, EditText text, boolean show) {
        InputMethodManager imm = (InputMethodManager) cntxt
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show){
            imm.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
        }else
            imm.hideSoftInputFromWindow(text.getWindowToken(), 0);
    }



}
