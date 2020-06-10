package com.secondary.alalamiastorez;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

class Product implements Parcelable {
    private String price, oldPrice, title, rate, specialTag, cpu, ram, storage, camera, sim, battery, screen, manufacturer, specialAdds, details;
    private ArrayList<String> img, colors;

    public Product() {
    }

    public Product(String price, String oldPrice, String title, String rate, ArrayList<String> img, String specialTag) {
        this.price = price;
        this.oldPrice = oldPrice;
        this.title = title;
        this.rate = rate;
        this.img = img;
        this.specialTag = specialTag;
    }

    public Product(String price, String oldPrice, String title, String cpu, String ram, String storage, String camera, String sim, String battery, String screen, String manufacturer, String specialAdds, String details, ArrayList<String> colors, ArrayList<String> img) {
        this.price = price;
        this.oldPrice = oldPrice;
        this.title = title;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.camera = camera;
        this.sim = sim;
        this.battery = battery;
        this.screen = screen;
        this.manufacturer = manufacturer;
        this.specialAdds = specialAdds;
        this.details = details;
        this.colors = colors;
        this.img = img;
    }

    protected Product(Parcel in) {
        price = in.readString();
        oldPrice = in.readString();
        title = in.readString();
        rate = in.readString();
        specialTag = in.readString();
        cpu = in.readString();
        ram = in.readString();
        storage = in.readString();
        camera = in.readString();
        sim = in.readString();
        battery = in.readString();
        screen = in.readString();
        manufacturer = in.readString();
        specialAdds = in.readString();
        details = in.readString();
        colors = in.createStringArrayList();
        img = in.createStringArrayList();
    }


    public String getSpecialAdds() {
        return specialAdds;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public static Creator<Product> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public void setSpecialAdds(String specialAdds) {
        this.specialAdds = specialAdds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSpecialTag() {
        return specialTag;
    }

    public void setSpecialTag(String specialTag) {
        this.specialTag = specialTag;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ArrayList<String> getImg() {
        return img;
    }

    public void setImg(ArrayList<String> img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(oldPrice);
        dest.writeString(title);
        dest.writeString(rate);
        dest.writeString(specialTag);
        dest.writeString(cpu);
        dest.writeString(ram);
        dest.writeString(storage);
        dest.writeString(camera);
        dest.writeString(sim);
        dest.writeString(battery);
        dest.writeString(screen);
        dest.writeString(manufacturer);
        dest.writeString(specialAdds);
        dest.writeString(details);
        dest.writeStringList(colors);
        dest.writeStringList(img);
    }
}
