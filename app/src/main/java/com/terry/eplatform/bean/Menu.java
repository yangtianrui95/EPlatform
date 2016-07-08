package com.terry.eplatform.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangtianrui on 16-7-8.
 * <p/>
 * 菜单的实体类，对应每一个菜单项
 */
public class Menu implements Parcelable {
    private String sub; // 菜单名称
    private ArrayList subdata;// 所有商品服务

    protected Menu(Parcel in) {
        sub = in.readString();
        // 必须使用当前线程的上下文对象进行获取
        subdata = in.readArrayList(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    // 写入Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sub);
        // ?
        parcel.writeArray(subdata.toArray());
    }
}
