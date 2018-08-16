package com.uppayplugin.unionpay.javabasetes.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/10/16
 * Time: 16:05
 */

public class CountyCodeModel implements Parcelable {

    public String countryCode;
    public String chnDesc;
    public String engDesc;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.countryCode);
        dest.writeString(this.chnDesc);
        dest.writeString(this.engDesc);
    }

    public CountyCodeModel() {
    }

    protected CountyCodeModel(Parcel in) {
        this.countryCode = in.readString();
        this.chnDesc = in.readString();
        this.engDesc = in.readString();
    }

    public static final Creator<CountyCodeModel> CREATOR = new Creator<CountyCodeModel>() {
        @Override
        public CountyCodeModel createFromParcel(Parcel source) {
            return new CountyCodeModel(source);
        }

        @Override
        public CountyCodeModel[] newArray(int size) {
            return new CountyCodeModel[size];
        }
    };
}
