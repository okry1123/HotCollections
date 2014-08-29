package com.threedroid.hotcollections.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.threedroid.hotcollections.activity.GameActivity;
import com.threedroid.hotcollections.adapter.RankingListAdapter;
import com.threedroid.hotcollections.bean.GameData;
import com.threedroid.hotcollections.bean.GameListResponse;
import com.threedroid.hotcollections.http.HttpManager;
import com.threedroid.hotcollections.http.ServerApi;
import com.threedroid.hotcollections.util.ConstValue;

/**
 * Created by mr on 14-8-26.
 */
public class NewListFragment extends android.support.v4.app.ListFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HttpManager.getInstance().doGetHomeList(GameData.SHOW_TYPE_ZX_HOME, 0, ConstValue.PAGE_SIZE, new Response.Listener<GameListResponse>() {
            @Override
            public void onResponse(GameListResponse response) {
                setListAdapter(new RankingListAdapter(response.list));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = ServerApi.SERVER_URL + "online/" + ((GameData)adapterView.getItemAtPosition(i)).game_id;
//                Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
