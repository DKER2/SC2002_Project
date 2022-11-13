package src.Entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 Represent a MovieGoer
 @author SE4G4
 @version 1.0
 @since 2022-11-09
*/
public class MovieGoer extends User{
    private static final long serialVersionUID = 3L;

    /**
	* Name of User
	*/
    private String name;

    /**
	* Email of User
	*/
    private String email;

    /**
	* Phone of User
	*/
    private String phone;

    /**
	* Age of User
	*/
    private Integer age;

    /**
	* Booking List of User
	*/
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();

    /**
	 * Constructor for MovieGoer Object
	 * @param username username of user
	 * @param password password of user
     * @param name username of user
     * @param email username of user
     * @param phone phone of user
     * @param age age of user
	 */
    public MovieGoer(String username, String password, String name, String email, String phone, Integer age) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.bookingList = new ArrayList<Booking>();
    }

    /**
     * This method returns the username of user
     * @return username of user
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the email of user
     * @return the email of user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * This method returns the phone of user
     * @return the phone of user
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * This method returns the age of user
     * @return the age of user
     */
    public int getAge() {
        return this.age;
    }

    /**
     * This method returns the booking list of user
     * @return the booking list of user
     */
    public ArrayList<Booking> getBookingList() {
        return this.bookingList;
    }
    
    /**
     * This method add new booking to booking list
     * @param newBooking new booking
     */
    public void booking(Booking newBooking){
        bookingList.add(newBooking);
    }
}
