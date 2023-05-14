package com.ctis487.ahmetoguzergin.hw2.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.ctis487.ahmetoguzergin.hw2.Interfaces.Behavior;

public class Person implements Parcelable, Behavior {
    private String name;
    private String eMail;
    private String password;
    private int id;
    private static int lastUsedId = 1;


    public Person(String name, String eMail, String password) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;
        this.id = lastUsedId;
        lastUsedId++;
    }

    // parcellable implementation

    protected Person(Parcel in) {
        name = in.readString();
        eMail = in.readString();
        password = in.readString();
        id = in.readInt();
        lastUsedId = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    // parcellable implementation end

    // getters
    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
    // getters end


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                "}";
    }

    // parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(eMail);
        parcel.writeString(password);
        parcel.writeInt(id);
        parcel.writeInt(lastUsedId);
    }
    // parcelable methods end

    @Override
    public String displayType() {
        return null;
    }
}

