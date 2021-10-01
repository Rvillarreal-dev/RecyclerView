package com.example.recyclerview.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable {
    private String name;
    private String subject;
    private String body;
    private String imageUrl;

    public Email(String name, String subject, String body, String imageUrl) {
        this.name = name;
        this.subject = subject;
        this.body = body;
        this.imageUrl = imageUrl;
    }

    protected Email(Parcel in) {
        name = in.readString();
        subject = in.readString();
        body = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(subject);
        parcel.writeString(body);
        parcel.writeString(imageUrl);
    }
}
