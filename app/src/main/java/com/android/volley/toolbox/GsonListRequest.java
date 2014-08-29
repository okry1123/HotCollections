package com.android.volley.toolbox;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson.
 */
public class GsonListRequest<T> extends Request<List<T>> {
    private final Gson gson = new Gson();
    private final Map<String, String> headers;
    private final Listener<List<T>> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param class Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonListRequest(String url, Map<String, String> headers,
            Listener<List<T>> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.headers = headers;
        this.listener = listener;
    }
    public GsonListRequest(int http, String url,
                           Listener<List<T>> listener, ErrorListener errorListener) {
        super(http, url, errorListener);
        this.listener = listener;
        headers = null;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(List<T> response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<List<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            Type listType = new TypeToken<List<T>>(){}.getType();
            List<T> list = gson.fromJson(json, listType);
            return Response.success(list, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}