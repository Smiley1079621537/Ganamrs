package com.lemuel.ganamrs.entity;

public class Subject {

    private String imageUrl;//封面
    private String title;//课程名
    private String speaker;//讲员
    private String movieId;//详情页id
    private String type;//类型
    private String source;//出处

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

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", speaker='" + speaker + '\'' +
                ", movieId='" + movieId + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
