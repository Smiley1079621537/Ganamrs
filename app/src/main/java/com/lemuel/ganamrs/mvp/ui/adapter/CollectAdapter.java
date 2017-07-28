package com.lemuel.ganamrs.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.ui.holder.AdoreViewHolder;

import java.util.List;

public class CollectAdapter extends BaseQuickAdapter<GankGril, AdoreViewHolder> {

    public CollectAdapter(@Nullable List<GankGril> data) {
        super(R.layout.item_collect, data);
    }

    @Override
    protected void convert(AdoreViewHolder helper, GankGril item) {
        SimpleDraweeView imageView = helper.getView(R.id.ivImage);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        float density = mContext.getResources().getDisplayMetrics().density;
        params.height = (int) (density* (150  + Math.random() * 100 ));
        imageView.setLayoutParams(params);
        imageView.setImageURI(item.getUrl());
    }
}
