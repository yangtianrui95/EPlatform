package com.terry.eplatform.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yangtianrui on 16-7-8.
 * <p/>
 * 菜单的实体类，对应每一个菜单项
 */
public class MenuItem implements Parcelable {
    private String sub; // 菜单名称
    private ArrayList subdata;// 所有商品服务


    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public ArrayList getSubdata() {
        return subdata;
    }

    public void setSubdata(ArrayList subdata) {
        this.subdata = subdata;
    }

    protected MenuItem(Parcel in) {
        sub = in.readString();
        // 必须使用当前线程的上下文对象进行获取
        subdata = in.readArrayList(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
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
