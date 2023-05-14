package com.ctis487.ahmetoguzergin.hw2.Database;

public class Person_Helper {

    private int id;
    private String name;
    private String email;
    private String password;

    public Person_Helper(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Person_Helper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "}\n\n";
    }
}
