package com.zomatosampleapp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


import com.zomatosampleapp.interfaces.ServerCallback;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import java.util.ArrayList;
/**
 * Created by Ramesh on 5/3/16.
 */

public class RequestManager<T> {

    public static final boolean IS_LIVE = true;


    public static final String TESTING_SERVER = "https://developers.zomato.com/api/";

    public static final String LIVE_SERVER = "https://developers.zomato.com/api/";
    public static final String VERSION = "v2.1/";

    public static final String TEST_BASEFEED_URL = TESTING_SERVER+VERSION;
    public static final String LIVE_BASEFEED_URL = LIVE_SERVER+VERSION;


    public static RequestManager instance;
    protected String LOG_TAG = "RequestManager";
    private Context mCtx;
    private RequestQueue mRequestQueue;
    private ArrayList<GsonRequest<T>> requestArr = new ArrayList<GsonRequest<T>>();
    private Context mContext;
    private String baseFeedURL;

    /*
     * Constructor
     */
    public RequestManager(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        baseFeedURL = "";
        if (RequestManager.IS_LIVE)
            baseFeedURL = LIVE_BASEFEED_URL;
        else
            baseFeedURL = TEST_BASEFEED_URL;

    }

    /*
     * Singleton Initialization
     */
    public static RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            DefaultHttpClient mDefaultHttpClient = new DefaultHttpClient();

            final ClientConnectionManager mClientConnectionManager = mDefaultHttpClient.getConnectionManager();
            final HttpParams mHttpParams = mDefaultHttpClient.getParams();
            final ThreadSafeClientConnManager mThreadSafeClientConnManager = new ThreadSafeClientConnManager(mHttpParams, mClientConnectionManager.getSchemeRegistry());
            mDefaultHttpClient = new DefaultHttpClient(mThreadSafeClientConnManager, mHttpParams);
            final HttpStack httpStack = new HttpClientStack(mDefaultHttpClient);


            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), httpStack);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    /*
     * Method to create the request from the passed parameters and place a Volley request
     */
    public void placeRequest(String methodName, Class<T> clazz, ServerCallback<T> listener, HashMap<String, String> feedParams, boolean isPOST) {

        String feedurl = baseFeedURL.concat(methodName);

        if (!isPOST && feedParams != null) {
            feedurl = createGetWithParams(feedurl, feedParams);
        }


        Log.d(LOG_TAG, "SearchURL::" + feedurl);
        GsonRequest<T> jsObjRequest = new GsonRequest<T>(isPOST ? Request.Method.POST : Request.Method.GET,
                feedurl,
                methodName,
                clazz,
                feedParams,
                listener,
                createSuccessListener(),
                createErrorListener());


        if (feedParams != null && !feedParams.containsKey(Constants.AUTHKEY)) {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put(Constants.AUTHKEY,  new Constants().getApiKey());
            jsObjRequest.setHeaders(headers);
        }

        jsObjRequest.setTag("ZM");
        int socketTimeout = 30000;//30 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);


        addToRequestQueue(jsObjRequest);

    }

    private String createGetWithParams(String url, HashMap<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (value != null) {
                try {
                    value = URLEncoder.encode(String.valueOf(value), HTTP.UTF_8);
                    if (builder.length() > 0)
                        builder.append("&");
                    builder.append(key).append("=").append(value);
                } catch (UnsupportedEncodingException e) {
                    Log.d("RequestManager", "UnsupportedEncodingException : "+e.toString());
                }
            }
        }

        return (url += "?" + builder.toString());
    }

    private IQListener<T> createSuccessListener() {
        return new IQListener<T>() {

            @Override
            public void onResponse(T response, GsonRequest<T> request) {
                try {
                    Log.i(LOG_TAG, request.getApiMethodName() + ": onResponse called");
                    request.getSourceListener().onAPIResponse(response, request.getApiMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onErrorResponse(VolleyError error, GsonRequest<T> request) {
                try {
                    Log.i(LOG_TAG, request.getApiMethodName() + ": onErrorResponse called");
                    request.getSourceListener().onErrorResponse(error, request.getApiMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onResponse(T response) {
                // TODO Auto-generated method stub

            }


        };
    }

    private Response.ErrorListener createErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LOG_TAG, "RequestManager : VolleyError");
            }
        };
    }

    /*
     * Method to stop the requests currently in the queue
	 */
    public void stop() {
        mRequestQueue.stop();

    }

    /*
     * Method to start processing the requests in the queue
     */
    public void start() {
        mRequestQueue.start();

    }

    /*
     * Method to cancel all the requests in the queue
     */
    public void cancelAll() {
        requestArr.clear();
        mRequestQueue.cancelAll("ZM");
    }


    /**
     * Customized Callback interface for delivering parsed responses.
     */
    public interface IQListener<T> extends Response.Listener<T> {
        /**
         * Called when a response is received.
         */
        public void onResponse(T response, GsonRequest<T> request);

        public void onErrorResponse(VolleyError error, GsonRequest<T> request);

    }
}
