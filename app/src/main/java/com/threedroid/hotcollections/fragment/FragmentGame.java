package com.threedroid.hotcollections.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.threedroid.hotcollections.MyApplication;
import com.threedroid.hotcollections.R;
import com.threedroid.hotcollections.onekeyshare.OnekeyShare;
import com.threedroid.hotcollections.util.Util;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.favorite.WechatFavorite;

/**
 * Created by Administrator on 2014-8-28.
 */
public class FragmentGame extends android.support.v4.app.Fragment {

    private String mUrl;
    private WebView mWebView;
    private ProgressBar mProgress;

    private String mTitle;

    public static FragmentGame launch(String url){
        FragmentGame fragment = new FragmentGame();
        Bundle b = new Bundle();
        b.putString("url", url);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = getArguments().getString("url");

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add(0, 0, 0, "分享");
        item.setIcon(R.drawable.share);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 0){
            showShare();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, null);
        mWebView = (WebView) root.findViewById(R.id.webView);
        mProgress = (ProgressBar) root.findViewById(R.id.progressBar);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                title = title.replaceAll("-7724游戏", "");
                title = title.replaceAll("7724游戏", "");
                mTitle = title;
                getActivity().setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);

                if(newProgress > 20){
                    mProgress.setVisibility(View.GONE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onPause() {
        super.onPause();

        mWebView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        mWebView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mWebView.setWebChromeClient(null);
        mWebView.setWebViewClient(null);
        mWebView.stopLoading();
        mWebView = null;
    }

    /**
     * 截取webView快照(webView加载的整个内容的大小)
     * @return
     */
    private Bitmap captureWebView(){
        Picture snapShot = mWebView.capturePicture();

        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),snapShot.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        return bmp;
    }

    private void showShare() {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Util.saveMyBitmap(captureWebView(), "liulanqijietu.png");
                    }
                }
        );
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                Util.saveMyBitmap(captureWebView(), "liulanqijietu.png");
//            }
//        });

        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        oks.addHiddenPlatform(WechatFavorite.NAME);
//        oks.setViewToShare(mWebView);
        //关闭sso授权
//        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字
        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("" + mTitle);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(MyApplication.getAppContext().getExternalCacheDir().getAbsolutePath() + "liulanqijietu.png");
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getActivity());
    }
}
