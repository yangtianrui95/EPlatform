package com.terry.eplatform.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangtianrui on 16-7-8.
 * <p/>
 * 所有商品的实体类
 * 仅提取关键信息
 */
public class Merchants implements Parcelable {

    private String mer_name; // 商品名称
    private String description; // 描述
    private String imgpath; // 图片路径
    private int market_price; // 市场价
    private float price; // 现价
    private String store_name; // 商店名称


    public Merchants(String description, String imgpath, int market_price, float price, String store_name) {
        this.description = description;
        this.imgpath = imgpath;
        this.market_price = market_price;
        this.store_name = store_name;
        this.price = price;
    }

    protected Merchants(Parcel in) {
        description = in.readString();
        imgpath = in.readString();
        market_price = in.readInt();
        price = in.readInt();
        store_name = in.readString();
    }

    // 读取接口，从Parcel中读取一个实现了Parcelable类的实例
    // 通过摸板参数的传递，分别返回一个或多个实现类的实例
    public static final Creator<Merchants> CREATOR = new Creator<Merchants>() {
        @Override
        public Merchants createFromParcel(Parcel in) {

            return new Merchants(in);
        }

        @Override
        public Merchants[] newArray(int size) {

            return new Merchants[size];
        }
    };

    // 内容描述接口,无需重写
    @Override
    public int describeContents() {
        return 0;
    }

    // 写入接口函数，打包
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(market_price);
        parcel.writeFloat(price);
        parcel.writeString(description);
        parcel.writeString(imgpath);
        parcel.writeString(store_name);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public int getMarket_price() {
        return market_price;
    }

    public void setMarket_price(int market_price) {
        this.market_price = market_price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getMer_name() {
        return mer_name;
    }

    public void setMer_name(String mer_name) {
        this.mer_name = mer_name;
    }

    @Override
    public String toString() {
        return "Merchants{" +
                "description='" + description + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", market_price=" + market_price +
                ", price=" + price +
                ", store_name='" + store_name + '\'' +
                '}';
    }
}
