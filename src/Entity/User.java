package src.Entity;

import java.io.Serializable;

/**
 * This class contains all the information of a user
 */

public class User implements Serializable {
    /**
	 * The username of the user
	 */
    private String username;

    /**
	 * The password of the user
	 */
    private String password;

    /**
	 * The age of the user
	 */
    private Integer age;

    /**
     * This method creates a user object with the given username, password
     * @param username the username of the admain
     * @param password the password of the admin
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    /**
     * This method creates a user object with the given username, password
     * @param username the username of the user
     * @param password the password of the user
     * @param age the age of user
     */
    public User(String username, String password, Integer age){
        this.username = username;
        this.password = password;
        this.age = age;
    }

    /**
     * This method returns the username of the user
     * @return the username of the user
     */	
    public String getUsername(){
        return username;
    }

    /**
     * This method returns the password of the user
     * @return the password of the user
     */	
    public String getPassword(){
        return password;
    }

    /**
     * This method set the username of the user
     * @param username the new username of the user
     */	
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * This method set the password of the user
     * @param password the new password of the user
     */	
    public void setPassword(String password){
        this.password = password;
    }
}
