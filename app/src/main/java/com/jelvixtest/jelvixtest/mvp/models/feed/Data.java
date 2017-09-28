package com.jelvixtest.jelvixtest.mvp.models.feed;

import android.os.Parcel;
import android.os.Parcelable;

import com.jelvixtest.jelvixtest.common.CheckNull;

public class Data implements Parcelable{

    private int id;
    private String first_name;
    private String last_name;
    private String avatar;

    public Data() {
    }

    protected Data(Parcel in) {
        id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        avatar = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        CheckNull.check(first_name, this::setFirst_name, () -> setFirst_name(""));
        return first_name;
    }

    public String getLast_name() {
        CheckNull.check(last_name, this::setLast_name, () -> setLast_name(""));
        return last_name;
    }

    public String getAvatar() {
        CheckNull.check(avatar, this::setAvatar, () -> setAvatar(""));
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    private void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    private void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(avatar);
    }
}
