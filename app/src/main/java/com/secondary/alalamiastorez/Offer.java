package com.secondary.alalamiastorez;

class Offer {
    private String title,details,img,fimg;
    //TODO: add action to offer

    public Offer(String title, String details, String img, String fimg) {
        this.title = title;
        this.details = details;
        this.img = img;
        this.fimg = fimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFimg() {
        return fimg;
    }

    public void setFimg(String fimg) {
        this.fimg = fimg;
    }
}
