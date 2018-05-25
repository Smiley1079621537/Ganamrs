package com.lemuel.ganamrs.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerMainComponent;
import com.lemuel.ganamrs.di.module.MainModule;
import com.lemuel.ganamrs.mvp.contract.MainContract;
import com.lemuel.ganamrs.mvp.presenter.MainPresenter;
import com.lemuel.ganamrs.mvp.ui.fragment.CollectFragment;
import com.lemuel.ganamrs.mvp.ui.fragment.GankGrilsFragment;
import com.lemuel.ganamrs.mvp.ui.fragment.GospelFragment;
import com.lemuel.ganamrs.mvp.ui.fragment.HomeFragment;
import com.lemuel.ganamrs.utils.FragmentUtils;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.container)
    LinearLayout mContainer;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    private int mReplace = 0;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private CollectFragment mCollectFragment;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mCollectFragment = CollectFragment.newInstance();
        mToolbarTitle.setText(R.string.title_home);
        mNavigation.setOnNavigationItemSelectedListener(this);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(GankGrilsFragment.newInstance());
        mFragments.add(mCollectFragment);
        mFragments.add(GospelFragment.newInstance());
        FragmentUtils.addFragments(getSupportFragmentManager(), mFragments, R.id.content, 0);
    }

    @Override
    public void showLoading() {

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mReplace = 0;
                mToolbarTitle.setText(R.string.title_home);
                break;
            case R.id.navigation_dashboard:
                mReplace = 1;
                mToolbarTitle.setText(R.string.title_dashboard);
                break;
            case R.id.navigation_notifications:
                mReplace = 2;
                mToolbarTitle.setText(R.string.title_notifications);
                mCollectFragment.setRefresh();
                break;
            case R.id.navigation_gospel:
                mReplace = 3;
                mToolbarTitle.setText(R.string.title_gospel);
                break;
        }
        FragmentUtils.hideAllShowFragment(mFragments.get(mReplace));
        return true;
    }
}
