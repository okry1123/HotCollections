package com.threedroid.hotcollections.http;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.threedroid.hotcollections.MyApplication;
import com.threedroid.hotcollections.bean.GameData;
import com.threedroid.hotcollections.bean.GameListResponse;

import java.util.List;

/**
 * Created by Administrator on 2014-8-28.
 */
public class HttpManager {
    private static HttpManager mInstance;
    public static HttpManager getInstance(){
        if(mInstance == null){
            mInstance = new HttpManager();
        }
        return mInstance;
    }

    RequestQueue mRequestQueue;
    HttpManager(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public void doGetHomeList(String showtype, int page, int pagesize, Response.Listener<GameListResponse> l, Response.ErrorListener el){
        GameDataHomeListRequest request = new GameDataHomeListRequest(showtype, page, pagesize, l, el);
        request.setShouldCache(true);
        send(request);
    }

    public void doGetTypeList(String showtype, String gametype, int page, int pagesize, Response.Listener<GameListResponse> l, Response.ErrorListener el){
        GameDataTypeListRequest request = new GameDataTypeListRequest(showtype, gametype, page, pagesize, l, el);
        request.setShouldCache(true);
        send(request);
    }

    public void send(Request request){
        mRequestQueue.add(request);
    }
}
