package com.lemuel.ganamrs.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.entity.Subject;
import com.lemuel.ganamrs.mvp.ui.holder.AdoreViewHolder;

import java.util.List;

public class GospelAdapter extends BaseQuickAdapter<Subject, AdoreViewHolder> {

    public GospelAdapter(@Nullable List<Subject> data) {
        super(R.layout.item_gospel, data);
    }

    @Override
    protected void convert(AdoreViewHolder helper, Subject item) {
        String title = item.getTitle();
        if (item.getTitle().contains("-")) {
            String[] split = item.getTitle().split("-");
            title = split[0];
        }
        helper.setText(R.id.title, title);
        helper.setText(R.id.speaker, item.getSpeaker());
        helper.setText(R.id.type, item.getType());
        helper.setText(R.id.source, item.getSource());
        ((SimpleDraweeView) helper.getView(R.id.image)).setImageURI(item.getImageUrl());
    }
}
