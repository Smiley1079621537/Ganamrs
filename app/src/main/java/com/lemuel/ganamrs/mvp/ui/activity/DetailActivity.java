package com.lemuel.ganamrs.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerDetailComponent;
import com.lemuel.ganamrs.di.module.DetailModule;
import com.lemuel.ganamrs.entity.GankBean;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.DetailContract;
import com.lemuel.ganamrs.mvp.presenter.DetailPresenter;
import com.lemuel.ganamrs.utils.AdoreUtil;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.lemuel.ganamrs.R.id.toolbar;


public class DetailActivity extends BaseActivity<DetailPresenter> implements DetailContract.View {


    private static final String APP_CACHE_DIRNAME = "web_cache";
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    private GankBean mGankBean;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .detailModule(new DetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        AdoreUtil.setTransparentStatusbar(this);
        mGankBean = (GankBean) getIntent().getSerializableExtra("gankBean");
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbar.setTitleEnabled(true);
        mCollapsingToolbar.setTitle(getString(R.string.app_name));
        initWebView();
        mPresenter.getRandomGril();
    }

    public void initWebView() {
        //mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK

        mWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        // 开启DOM storage API 功能
        mWebview.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mWebview.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath()
                + APP_CACHE_DIRNAME;
        Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        mWebview.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        mWebview.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        mWebview.getSettings().setAppCacheEnabled(true);
        Log.i("databasepath", mWebview.getSettings().getDatabasePath());
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                Log.i(TAG, "onLoadResource url=" + url);
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "intercept url=" + url);
                view.loadUrl(url);
                return true;
            }

            // 页面开始时调用
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageStarted");
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成调用
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG)
                        .show();
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                Log.e(TAG, "onJsAlert " + message);
                Toast.makeText(getApplicationContext(), message,
                        Toast.LENGTH_SHORT).show();
                result.confirm();
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url,
                                       String message, JsResult result) {
                Log.e(TAG, "onJsConfirm " + message);
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, JsPromptResult result) {
                Log.e(TAG, "onJsPrompt " + url);
                return super.onJsPrompt(view, url, message, defaultValue,
                        result);
            }
        });
        mWebview.loadUrl(mGankBean.getUrl());
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "smile:)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void loadGril(GankGril gril) {
        Glide.with(this).load(gril.getUrl()).asBitmap().into(mImageView);
    }
}
