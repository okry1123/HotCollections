package com.threedroid.hotcollections.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.GsonListRequest;
import com.android.volley.toolbox.GsonRequest;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.threedroid.hotcollections.bean.GameData;
import com.threedroid.hotcollections.bean.GameListResponse;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014-8-28.
 */
public class GameDataHomeListRequest extends Request<GameListResponse> {

    private final Gson gson = new Gson();
    private final Response.Listener<GameListResponse> listener;

    private String mShowType;
    private int mPage, mPageSize;
    public GameDataHomeListRequest(String showType, int page, int pagesize, Response.Listener<GameListResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, ServerApi.GET_GAME_LIST, errorListener);

        mShowType = showType;
        mPage = page;
        mPageSize = pagesize;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        //头文件不要动，已经是我筛选过的了，再少很容易服务器校验不过
        //Host: online.7724.com
        //Accept: application/json, text/javascript, */*; q=0.01
        //X-Requested-With: XMLHttpRequest
        //Content-Type: application/x-www-form-urlencoded; charset=UTF-8
        //Referer: http://online.7724.com/
        //Accept-Encoding: gzip,deflate,sdch
        //Content-Length: 75
        //User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Host" , "online.7724.com");
        headers.put("Accept" , "application/json, text/javascript, */*; q=0.01");
        headers.put("X-Requested-With" , "XMLHttpRequest");
        headers.put("Content-Type" , "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Referer" , "http://online.7724.com/");
//        headers.put("Accept-Encoding" , "gzip,deflate,sdch");
//        headers.put("Content-Length" , "75");
        headers.put("User-Agent" , "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
//        r=getitem/getdata
//        showtype=online_ph（排行）online_zx（最新）
//        gametype=0
//        page=4
//        pagesize=10
//        keyword=
        Map<String, String> post = new HashMap<String, String>();
        post.put("r", "getitem/getdata");
        post.put("showtype", mShowType);
        post.put("gametype", "0");
        post.put("page", String.valueOf(mPage));
        post.put("pagesize", String.valueOf(mPageSize));
        return post;
    }

    @Override
    protected void deliverResponse(GameListResponse response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<GameListResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, GameListResponse.class), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
