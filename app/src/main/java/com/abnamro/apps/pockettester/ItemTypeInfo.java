package com.abnamro.apps.pockettester;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class ItemTypeInfo implements Parcelable {
    private final String mTopicId;
    private final String mTitle;
    private final List<ModuleInfo> mModules;

    public ItemTypeInfo(String topicId, String title, List<ModuleInfo> modules) {
        mTopicId = topicId;
        mTitle = title;
        mModules = modules;
    }

    private ItemTypeInfo(Parcel source) {
        mTopicId = source.readString();
        mTitle = source.readString();
        mModules = new ArrayList<>();
        source.readTypedList(mModules, ModuleInfo.CREATOR);
    }

    public String getTopicId() {
        return mTopicId;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemTypeInfo that = (ItemTypeInfo) o;

        return mTopicId.equals(that.mTopicId);

    }

    @Override
    public int hashCode() {
        return mTopicId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTopicId);
        dest.writeString(mTitle);
        dest.writeTypedList(mModules);
    }

    public static final Creator<ItemTypeInfo> CREATOR =
            new Creator<ItemTypeInfo>() {

                @Override
                public ItemTypeInfo createFromParcel(Parcel source) {
                    return new ItemTypeInfo(source);
                }

                @Override
                public ItemTypeInfo[] newArray(int size) {
                    return new ItemTypeInfo[size];
                }
            };

}
