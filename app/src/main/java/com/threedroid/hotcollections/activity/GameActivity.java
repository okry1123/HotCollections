package com.threedroid.hotcollections.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.fragment.FragmentGame;
import com.threedroid.hotcollections.fragment.MainFragment;

/**
 * Created by Administrator on 2014-8-28.
 */
public class GameActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, FragmentGame.launch(url))
                    .commit();
        }
    }
}
