package org.aplas.mecourse;

public class listMaterialModel {
    private String content1,content2,content3;
    private String title;
    private String uimg,uimg1,uimg2,uimg3;
    private String course;

    public listMaterialModel(){}

    public listMaterialModel(String course,String title,String img_h, String c1, String img1, String c2, String img2, String c3, String img3) {
        this.course = course;
        this.title = title;
        this.uimg = img_h;
        this.content1 = c1;
        this.uimg1 = img1;
        this.content2 = c2;
        this.uimg2 = img2;
        this.content3 = c3;
        this.uimg3 = img3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getUimg1() {
        return uimg1;
    }

    public void setUimg1(String uimg1) {
        this.uimg1 = uimg1;
    }

    public String getUimg2() {
        return uimg2;
    }

    public void setUimg2(String uimg2) {
        this.uimg2 = uimg2;
    }

    public String getUimg3() {
        return uimg3;
    }

    public void setUimg3(String uimg3) {
        this.uimg3 = uimg3;
    }
}
