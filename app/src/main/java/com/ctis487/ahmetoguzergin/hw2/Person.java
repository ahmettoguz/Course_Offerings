package com.ctis487.ahmetoguzergin.hw2;

import android.content.Context;
import android.widget.Toast;

public class Person {
    private String name;
    private String eMail;
    private String password;

    private int id;

    public Person(String name, String eMail, String password, int id) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;
        this.id = id;
    }

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
                '}';
    }
}

