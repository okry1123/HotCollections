package com.threedroid.hotcollections.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.fragment.MainFragment;
import com.umeng.update.UmengUpdateAgent;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UmengUpdateAgent.update(this);

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }

    private long lastPress;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && (System.currentTimeMillis() - lastPress) > 800){
            Toast.makeText(this, "双击返回键退出", Toast.LENGTH_SHORT).show();
            lastPress = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
