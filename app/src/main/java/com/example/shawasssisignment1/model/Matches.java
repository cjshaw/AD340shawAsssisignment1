package com.example.shawasssisignment1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Matches implements Parcelable {
    public String uid;
    public String name;
    public String imageUrl;
    public boolean liked;
    public String lat;
    public String longitude;

    public Matches() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Matches(String name, boolean liked, String imageUrl, String latitude, String longitude) {
        this.name = name;
        this.liked = liked;
        this.imageUrl = imageUrl;
        this.lat = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLatitude() {
        return lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public boolean isLiked() {
        return liked;
    }

    public static final Creator<Matches> CREATOR = new Creator<Matches>() {
        @Override
        public Matches createFromParcel(Parcel in) {
            return new Matches();
        }

        @Override
        public Matches[] newArray(int size) {
            return new Matches[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(name);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(longitude);
        dest.writeString(lat);
    }
}
