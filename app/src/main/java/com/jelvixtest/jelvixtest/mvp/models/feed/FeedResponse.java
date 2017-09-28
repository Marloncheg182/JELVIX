package com.jelvixtest.jelvixtest.mvp.models.feed;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FeedResponse implements Parcelable{
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<Data> data;

    protected FeedResponse(Parcel in) {
        page = in.readInt();
        perPage = in.readInt();
        total = in.readInt();
        totalPages = in.readInt();
        data = in.createTypedArrayList(Data.CREATOR);
    }

    public static final Creator<FeedResponse> CREATOR = new Creator<FeedResponse>() {
        @Override
        public FeedResponse createFromParcel(Parcel in) {
            return new FeedResponse(in);
        }

        @Override
        public FeedResponse[] newArray(int size) {
            return new FeedResponse[size];
        }
    };

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Data> getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(perPage);
        dest.writeInt(total);
        dest.writeInt(totalPages);
        dest.writeTypedList(data);
    }

    @Override
    public String toString() {
        return "FeedResponse{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", total=" + total +
                ", totalPages=" + totalPages +
                ", data=" + data +
                '}';
    }
}
