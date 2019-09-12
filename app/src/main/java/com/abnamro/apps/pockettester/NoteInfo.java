package com.abnamro.apps.pockettester;

import android.os.Parcel;
import android.os.Parcelable;

public final class NoteInfo implements Parcelable {
    private ItemTypeInfo mTopic;
    private String mTitle;
    private String mText;

    public NoteInfo(ItemTypeInfo mTopic, String title, String text) {
        this.mTopic = mTopic;
        mTitle = title;
        mText = text;
    }

    private NoteInfo(Parcel parcel) {
        mTopic = parcel.readParcelable(ItemTypeInfo.class.getClassLoader());
        mTitle = parcel.readString();
        mText = parcel.readString();
    }

    public ItemTypeInfo getTopic() {
        return mTopic;
    }

    public void setTopic(ItemTypeInfo topic) {
        mTopic = topic;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mTopic.getTopicId() + ": " + mTitle + "\r\n" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mTopic, 0);
        parcel.writeString(mTitle);
        parcel.writeString(mText);
    }

    public static final Creator<NoteInfo> CREATOR =
            new Creator<NoteInfo>() {
                @Override
                public NoteInfo createFromParcel(Parcel parcel) {
                    return new NoteInfo(parcel);
                }

                @Override
                public NoteInfo[] newArray(int size) {
                    return new NoteInfo[size];
                }
            };
}












