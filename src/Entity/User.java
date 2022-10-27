package src.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private Integer age;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Integer age){
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername(){
        return username;
    }
}
