package src.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieGoer extends User{
    private static final long serialVersionUID = 3L;

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

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getAge() {
        return this.age;
    }

    public ArrayList<Booking> getBookingList() {
        return this.bookingList;
    }
    
    public void booking(Booking newBooking){
        bookingList.add(newBooking);
    }
}
