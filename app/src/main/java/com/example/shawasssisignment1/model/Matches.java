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

    public Matches() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Matches(String name, boolean liked, String imageUrl) {
        this.name = name;
        this.liked = liked;
        this.imageUrl = imageUrl;
    }

    public Matches(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        liked = in.readByte() != 0;
    }

    public static final Creator<Matches> CREATOR = new Creator<Matches>() {
        @Override
        public Matches createFromParcel(Parcel in) {
            return new Matches(in);
        }

        @Override
        public Matches[] newArray(int size) {
            return new Matches[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("imageUrl", imageUrl);
        result.put("liked", liked);
        result.put("name", name);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(name);
        dest.writeByte((byte) (liked ? 1 : 0));
    }
}
