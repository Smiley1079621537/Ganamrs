package com.lemuel.ganamrs.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lemuel.ganamrs.R;
import com.lemuel.ganamrs.entity.GankBean;
import com.lemuel.ganamrs.mvp.ui.holder.AdoreViewHolder;

import java.util.List;


public class CategoryAdapter extends BaseQuickAdapter<GankBean,AdoreViewHolder> {

    public CategoryAdapter(@Nullable List<GankBean> data) {
        super(R.layout.item_android, data);
    }

    @Override
    protected void convert(AdoreViewHolder helper, GankBean item) {
        helper.setText(R.id.tvDesc, item.getDesc());
        ImageView ivImage = helper.getView(R.id.ivImage);
        if (item.getType().equals("Android")){
            ivImage.setImageResource(R.drawable.icon_android);
        } else if (item.getType().equals("iOS")){
            ivImage.setImageResource(R.drawable.icon_apple);
        }else if (item.getType().equals("前端")){
            ivImage.setImageResource(R.drawable.icon_html);
        }
    }
}
