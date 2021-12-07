package org.aplas.mecourse;

public class courseModel {
    private String title;
    private String iurl;
    private String category;

    public courseModel(){}

    public courseModel(String title, String iurl) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
