package com.lemuel.ganamrs.entity;

import java.io.Serializable;
import java.util.List;


public class GankBean implements Serializable{
    /**
     * _id : 5973f95d421aa90c9203d3eb
     * createdAt : 2017-07-23T09:18:21.828Z
     * desc : Android 层叠卡片控件，仿"探探app"
     * images : ["http://img.gank.io/36e2eb02-be78-4c97-950b-28020d1e0356"]
     * publishedAt : 2017-07-24T12:13:11.280Z
     * source : chrome
     * type : Android
     * url : https://github.com/fashare2015/StackLayout
     * used : true
     * who : Jason
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

