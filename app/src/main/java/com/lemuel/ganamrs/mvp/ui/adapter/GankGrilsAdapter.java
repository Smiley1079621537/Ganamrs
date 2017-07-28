package com.lemuel.ganamrs.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.ui.holder.AdoreViewHolder;

import java.util.List;


public class GankGrilsAdapter extends BaseQuickAdapter<GankGril, AdoreViewHolder> {

    public GankGrilsAdapter(@Nullable List<GankGril> data) {
        super(R.layout.item_girls, data);
    }

    @Override
    protected void convert(AdoreViewHolder helper, GankGril item) {
        ((SimpleDraweeView) helper.getView(R.id.ivImage)).setImageURI(item.getUrl());
    }
}