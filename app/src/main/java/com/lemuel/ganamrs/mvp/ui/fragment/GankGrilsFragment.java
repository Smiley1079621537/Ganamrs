package com.lemuel.ganamrs.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.di.component.DaggerGankGrilsComponent;
import com.lemuel.ganamrs.di.module.GankGrilsModule;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.GankGrilsContract;
import com.lemuel.ganamrs.mvp.presenter.GankGrilsPresenter;
import com.lemuel.ganamrs.mvp.ui.adapter.GankGrilsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class GankGrilsFragment extends BaseFragment<GankGrilsPresenter> implements GankGrilsContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    private GankGrilsAdapter mAdapter;


    public static GankGrilsFragment newInstance() {
        GankGrilsFragment fragment = new GankGrilsFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerGankGrilsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .gankGrilsModule(new GankGrilsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gank_grils, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getGankGrils(true);
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
    public void setGankGrils(final ArrayList<GankGril> gankGrils) {
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
        mAdapter = new GankGrilsAdapter(gankGrils);
        mRecyclerView.setAdapter(mAdapter);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(mRecyclerView.getAdapter(), gankGrils);
        cardCallback.setOnSwipedListener(new OnSwipeListener<GankGril>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, GankGril gril, int direction) {
                if (mAdapter.getData().size() < 4){
                    mPresenter.getGankGrils(false);
                }
                if(direction == CardConfig.SWIPED_RIGHT){
                    mPresenter.addToFavorites(gril);
                }
            }

            @Override
            public void onSwipedClear() {

            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(mRecyclerView, touchHelper);
        mRecyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void addGankGrils(ArrayList<GankGril> gankGrils) {
       mAdapter.addData(gankGrils);
    }
}
