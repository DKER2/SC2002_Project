package src.Entity;

import java.io.Serializable;
import java.util.Date;

public class Holiday implements Serializable{
    private Date datetime;

    private String name;

    public Holiday(Date datetime, String name){
        this.datetime = datetime;
        this.name = name;
    }

    public Date getDatetime() {
        return this.datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}
