package com.rj.eventapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EmailModel implements Parcelable
{

    int id;
    String name;
    String email;

    public EmailModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    protected EmailModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<EmailModel> CREATOR = new Creator<EmailModel>() {
        @Override
        public EmailModel createFromParcel(Parcel in) {
            return new EmailModel(in);
        }

        @Override
        public EmailModel[] newArray(int size) {
            return new EmailModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
    }
}
