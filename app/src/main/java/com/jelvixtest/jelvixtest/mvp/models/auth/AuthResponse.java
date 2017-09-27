package com.jelvixtest.jelvixtest.mvp.models.auth;

import android.os.Parcel;
import android.os.Parcelable;

import com.jelvixtest.jelvixtest.common.CheckNull;

public class AuthResponse implements Parcelable {

    private String token;

    protected AuthResponse(Parcel in) {
        token = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AuthResponse> CREATOR = new Creator<AuthResponse>() {
        @Override
        public AuthResponse createFromParcel(Parcel in) {
            return new AuthResponse(in);
        }

        @Override
        public AuthResponse[] newArray(int size) {
            return new AuthResponse[size];
        }
    };

    public String getToken() {
        CheckNull.check(token, this::setToken, ()-> setToken(""));
        return token;
    }

    private void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
