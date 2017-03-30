package model;

import java.util.Date;

/**
 * Created by Imant on 27.02.17.
 */
public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private String group;
    private String date;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String address, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.group = group;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public String toStringForSearch() {
        return "" + name + " " + phoneNumber + " " + address + " " + group + "";
    }

    public String toStringForLog() {
        return "" + name + " " + phoneNumber + " " + address + " " + group + "";
    }

}
