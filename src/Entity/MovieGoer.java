package src.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieGoer extends User{
    private String name;
    private String email;
    private String phone;
    private Integer age;
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();

    public MovieGoer(String username, String password, String name, String email, String phone, Integer age, ArrayList<Booking> bookingList) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.bookingList = bookingList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<Booking> getBookingList() {
        return this.bookingList;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }


}
