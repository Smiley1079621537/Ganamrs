package com.lemuel.ganamrs.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.GankGril;

import java.util.ArrayList;


public interface GankGrilsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void setGankGrils(ArrayList<GankGril> gankGrils);

        void addGankGrils(ArrayList<GankGril> gankGrils);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        void getRandomGrils(final AdoreCallback<ArrayList<GankGril>> callback);

        void addToFavorites(GankGril gril);
    }
}
