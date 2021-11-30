package org.aplas.mecourse;

public class categoryModel {
    private String name, iurl;

    public categoryModel(){}

    public categoryModel(String name, String iurl,long countlesson) {
        this.name = name;
        this.iurl = iurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }
}
