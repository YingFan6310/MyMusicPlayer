package com.example.fy.mymusic.model;

import java.io.Serializable;
public class SongListInfo implements Serializable {
    private String title;
    private String type;
    private String coverUrl;
    private String music1;
    private String music2;
    private String music3;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getMusic1() {
        return music1;
    }

    public void setMusic1(String music1) {
        this.music1 = music1;
    }

    public String getMusic2() {
        return music2;
    }

    public void setMusic2(String music2) {
        this.music2 = music2;
    }

    public String getMusic3() {
        return music3;
    }

    public void setMusic3(String music3) {
        this.music3 = music3;
    }
}
