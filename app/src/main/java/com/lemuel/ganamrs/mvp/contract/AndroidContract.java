package com.lemuel.ganamrs.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.GankBean;

import java.util.ArrayList;


public interface AndroidContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void setGankData(ArrayList<GankBean> gankBeen);

        void addGankData(ArrayList<GankBean> gankBeen);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        void getGankData(String type, int pageSize, int page, AdoreCallback<ArrayList<GankBean>> callback);
    }
}
