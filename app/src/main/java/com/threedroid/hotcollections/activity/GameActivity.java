package com.threedroid.hotcollections.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

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

//        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String url = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, FragmentGame.launch(url))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private long lastPress;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && (System.currentTimeMillis() - lastPress) > 800){
            Toast.makeText(this, "双击返回键退出游戏", Toast.LENGTH_SHORT).show();
            lastPress = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
