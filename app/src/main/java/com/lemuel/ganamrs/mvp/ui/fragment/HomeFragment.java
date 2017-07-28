package com.lemuel.ganamrs.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerHomeComponent;
import com.lemuel.ganamrs.di.module.HomeModule;
import com.lemuel.ganamrs.mvp.contract.HomeContract;
import com.lemuel.ganamrs.mvp.presenter.HomePresenter;
import com.lemuel.ganamrs.mvp.ui.adapter.TabPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;

    private String[] mTitles = new String[]{"绿毛峰", "碧螺春", "铁观音"};

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Fragment[] fragments = new Fragment[3];
        fragments[0] = CategoryFragment.newInstance("Android");
        fragments[1] = CategoryFragment.newInstance("iOS");
        fragments[2] = CategoryFragment.newInstance("前端");
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(
                getChildFragmentManager(), fragments);
        tabPagerAdapter
                .setTabTitles(mTitles);
        mViewpager.setAdapter(tabPagerAdapter);
        mViewpager.setOffscreenPageLimit(mTitles.length);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
