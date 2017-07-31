package com.lemuel.ganamrs.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerCollectComponent;
import com.lemuel.ganamrs.di.module.CollectModule;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.CollectContract;
import com.lemuel.ganamrs.mvp.presenter.CollectPresenter;
import com.lemuel.ganamrs.mvp.ui.adapter.CollectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemLongClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    private CollectAdapter mAdapter;
    private List<GankGril> mGankGrils;

    public static CollectFragment newInstance() {
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerCollectComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .collectModule(new CollectModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        UiUtils.configRecycleView(mRecyclerView,
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new CollectAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.layout_empty);
        mAdapter.setOnItemLongClickListener(this);
        mPresenter.getAllGrils();
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
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
    public void onRefresh() {
        mPresenter.getAllGrils();
    }

    public void setRefresh() {
        onRefresh();
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.removeThisGril(mGankGrils.get(position));
        onRefresh();
        return false;
    }

    @Override
    public void loadGrils(List<GankGril> gankGrils) {
        mGankGrils = gankGrils;
        mAdapter.setNewData(mGankGrils);
    }

    @Override
    public void showEmpty() {
        mGankGrils = new ArrayList<>();
        mAdapter.setNewData(null);
    }
}
