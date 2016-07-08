package com.terry.eplatform.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangtianrui on 16-7-8.
 * <p/>
 * 所有商品的实体类
 */
public class Merchants implements Parcelable {
    protected Merchants(Parcel in) {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
