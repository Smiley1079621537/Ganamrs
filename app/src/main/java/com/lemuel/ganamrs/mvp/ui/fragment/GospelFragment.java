package com.lemuel.ganamrs.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerGospelComponent;
import com.lemuel.ganamrs.di.module.GospelModule;
import com.lemuel.ganamrs.entity.Subject;
import com.lemuel.ganamrs.mvp.contract.GospelContract;
import com.lemuel.ganamrs.mvp.presenter.GospelPresenter;
import com.lemuel.ganamrs.mvp.ui.adapter.GospelAdapter;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class GospelFragment extends BaseFragment<GospelPresenter> implements GospelContract.View {


    @BindView(R.id.gospelListView)
    RecyclerView mGospelListView;
    private GospelAdapter mGospelAdapter;

    public static GospelFragment newInstance() {
        GospelFragment fragment = new GospelFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerGospelComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .gospelModule(new GospelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gospel, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        UiUtils.configRecycleView(mGospelListView, new GridLayoutManager(getActivity(), 1));
        mGospelAdapter = new GospelAdapter(null);
        mGospelListView.setAdapter(mGospelAdapter);
        mGospelAdapter.bindToRecyclerView(mGospelListView);
        mGospelAdapter.setEmptyView(R.layout.layout_empty);
        mPresenter.getGospel();
    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {
        Toast.makeText(getActivity(), "good!", Toast.LENGTH_SHORT).show();
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
    public void loadGospel(ArrayList<Subject> subjects) {
        mGospelAdapter.setNewData(subjects);
    }
}
