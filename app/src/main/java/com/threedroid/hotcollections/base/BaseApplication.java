package com.threedroid.hotcollections.base;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by mr on 14-7-18.
 */
public abstract class BaseApplication extends Application {

    private static BaseApplication sAppInstance;

    /**
     * 获取App实例
     * App为空时抛出npe
     *
     * @return PgCameraApplication getInstance
     */
    public static BaseApplication getAppInstance() {
        if (sAppInstance == null)
            throw new NullPointerException("App getInstance not create or be terminated");
        return sAppInstance;
    }

    /**
     * 取得Application Context
     * App为空时抛出npe
     *
     * @return Application Context, this getInstance of Application
     */
    public static Context getAppContext() {
        if (sAppInstance == null)
            throw new NullPointerException("App getInstance not create or be terminated");
        return sAppInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppInstance = this;
        initImageLoader();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 初始化ImageLoader相关
     */
    protected void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(configuration);
    }
}
