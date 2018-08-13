package com.uppayplugin.unionpay.javabasetes.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: LiuGq
 * Date: 2018/4/24
 * Time: 15:26
 */

public class TestEntity implements Parcelable {
    private String mStr;
    private int mData;


    //读数据进行恢复
    protected TestEntity(Parcel in) {
        mStr = in.readString();
        mData = in.readInt();
    }

    //用来创建自定义的Parcelable的 对象
    public static final Creator<TestEntity> CREATOR = new Creator<TestEntity>() {
        @Override
        public TestEntity createFromParcel(Parcel in) {
            return new TestEntity(in);
        }

        @Override
        public TestEntity[] newArray(int size) {
            return new TestEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //写数据进行保存
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStr);
        dest.writeInt(mData);
    }
}
