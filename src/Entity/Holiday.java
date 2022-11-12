package src.Entity;

import java.io.Serializable;
import java.util.Date;

public class Holiday implements Serializable{
    private static final long serialVersionUID = 5L;

    /**
     * date of Holiday
     */
    private Date datetime;

    /**
     * name of Holiday
     */
    private String name;

    /**
	 * Constructor for Holiday Object
	 * @param datetime date of Holiday
	 * @param name name of Holiday
	 */
    public Holiday(Date datetime, String name){
        this.datetime = datetime;
        this.name = name;
    }

    /**
	 * Get Holiday Date
     * @return Holiday Date
	 */
    public Date getDatetime() {
        return this.datetime;
    }

    /**
	 * Set Holiday Date
     * @param datetime new datetime
	 */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
	 * Get name of Holiday
     * @return name of Holiday
	 */
    public String getName() {
        return this.name;
    }

    /**
	 * Get name of Holiday
     * @param name new name of Holiday
	 */
    public void setName(String name) {
        this.name = name;
    }    
}
