package com.lemuel.ganamrs.mvp.model;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.Subject;
import com.lemuel.ganamrs.mvp.contract.GospelContract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;


@ActivityScope
public class GospelModel extends BaseModel implements GospelContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public GospelModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void getGospel(final AdoreCallback<ArrayList<Subject>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<Subject> subjects = new ArrayList<>();
                    Document doc = Jsoup.connect("https://www.fuyin.tv/index.php/content/category/id/290/").get();
                    Elements elements = doc.getElementsByClass("list").select("li");//li行
                    for (int i = 0; i < elements.size(); i++) {
                        Element dl = elements.get(i).getElementsByTag("dl").get(0);//dl行

                        Element dt = dl.getElementsByTag("dt").get(0);//dt0行
                        String movieId = dt.getElementsByTag("a").get(0).attr("href");//详情页id
                        String imgUrl = dt.getElementsByTag("img").get(0).attr("_src");//封面
                        String title = dt.getElementsByTag("img").get(0).attr("alt");//课程名

                        Element dd1 = elements.get(i).getElementsByTag("dd").get(1);//dd1行
                        String type = dd1.getElementsByTag("a").get(0).html();//类型

                        Element dd2 = elements.get(i).getElementsByTag("dd").get(2);//dd2行
                        String source = dd2.getElementsByTag("span").get(1).html();//出处

                        Element dd3 = elements.get(i).getElementsByTag("dd").get(3);//dd3行
                        String speaker = dd3.getElementsByTag("a").get(0)
                                .getElementsByTag("span").get(0).html();//讲员

                        Subject subject = new Subject();
                        subject.setMovieId(movieId);
                        subject.setImageUrl(imgUrl);
                        subject.setTitle(title);
                        subject.setSpeaker(speaker);
                        subject.setType(type);
                        subject.setSource(source);
                        Log.i("dl", subject.toString());

                        subjects.add(subject);
                    }
                    callback.onSuccess(subjects);
                } catch (IOException mE) {
                    mE.printStackTrace();
                }
            }
        }).start();
    }
}