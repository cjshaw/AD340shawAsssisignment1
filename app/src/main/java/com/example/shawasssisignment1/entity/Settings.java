package com.example.shawasssisignment1.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "settings")
public class Settings {
    @PrimaryKey
    @NonNull
    private String appId = "clint_app";

    @ColumnInfo(name = "match_time_reminder")
    private String matchTimeReminder;

    @ColumnInfo(name = "max_distance")
    private String maxDistance;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "profile_public")
    private boolean profilePublic = false;

    @ColumnInfo(name = "min_age")
    private int minAge;

    @ColumnInfo(name = "max_age")
    private int maxAge;

    @NonNull
    public String getAppId() {
        return appId;
    }

    public void setAppId(@NonNull String appId) {
        this.appId = appId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfilePublic(boolean profilePublic) {
        this.profilePublic = profilePublic;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public String getMatchTimeReminder() {
        return matchTimeReminder;
    }

    public String getGender() {
        return gender;
    }

    public boolean isProfilePublic() {
        return profilePublic;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
    }

    public void setMatchTimeReminder(String matchTimeReminder) {
        this.matchTimeReminder = matchTimeReminder;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        String msg = "";
        msg += "appid: " + getAppId() + "\n";
        msg += "Gender selection: " + getGender() + "\n";
        msg += "Match Time selection: " + getMatchTimeReminder() + "\n";
        msg += "max distance: " + getMaxDistance() + " \n";
        msg += "min age: " + getMinAge() + "\n";
        msg += "max age: " + getMaxAge() + "\n";
        msg += "Profile Public status: =" + isProfilePublic();

        return msg;
    }
}
