package com.lemuel.ganamrs.mvp.ui.holder;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

public class AdoreViewHolder extends BaseViewHolder {

    public final Context mContext;

    public AdoreViewHolder(View view) {
        super(view);
        mContext = view.getContext();
    }
}
