package org.aplas.mecourse;

public class courseModel {
    private String title, iurl;

    public courseModel(){}

    public courseModel(String title, String iurl,long countlesson) {
        this.title = title;
        this.iurl = iurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }
}
