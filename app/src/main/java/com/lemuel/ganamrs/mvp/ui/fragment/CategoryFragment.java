package com.lemuel.ganamrs.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerCategoryComponent;
import com.lemuel.ganamrs.di.module.CategoryModule;
import com.lemuel.ganamrs.entity.GankBean;
import com.lemuel.ganamrs.mvp.contract.AndroidContract;
import com.lemuel.ganamrs.mvp.presenter.CategoryPresenter;
import com.lemuel.ganamrs.mvp.ui.activity.DetailActivity;
import com.lemuel.ganamrs.mvp.ui.adapter.CategoryAdapter;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class CategoryFragment extends BaseFragment<CategoryPresenter> implements AndroidContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    private CategoryAdapter mAdapter;
    private int mPage = 1;
    private int pageSize = 20;
    private ArrayList<GankBean> mGankBeen = new ArrayList<>();
    private String type;

    public static CategoryFragment newInstance(String type) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerCategoryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .categoryModule(new CategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        type = getArguments().getString("type");
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);
        mAdapter = new CategoryAdapter(mGankBeen);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.setAdapter(mAdapter);
        UiUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(getActivity()));
        mPresenter.getGankData(type, pageSize, mPage);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                launchActivity(new Intent(getActivity(), DetailActivity.class)
                        .putExtra("gankBean", (GankBean) adapter.getData().get(position)));
            }
        });
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
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
    public void setGankData(ArrayList<GankBean> gankBeen) {
        mAdapter.setNewData(gankBeen);
        hideLoading();
        mPage++;
    }

    @Override
    public void addGankData(ArrayList<GankBean> gankBeen) {
        mAdapter.addData(gankBeen);
        mAdapter.loadMoreComplete();
        hideLoading();
        mPage++;
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mPresenter.getGankData(type, pageSize, mPage);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getGankData(type, pageSize, mPage);
    }
}
