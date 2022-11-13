package src.Entity;

import java.io.Serializable;

/**
 This class contains all the information of a Review
 @author SE4G4
 @version 1.0
 @since 2022-11-09
*/
public class Review implements Serializable{
    private static final long serialVersionUID = 7L;

    /**
	* Statement of review
	*/
    private String statement;

    /**
	* Rate score of review
	*/
    private Integer rate;

    /**
	* Rate owner of review
	*/
    private MovieGoer movieGoer;

    /**
	 * Constructor for Review Object
	 * @param statement statement of review
	 * @param rate rate of review
     * @param movieGoer owner of review
	 */
    public Review(String statement, Integer rate, MovieGoer movieGoer) {
        this.statement = statement;
        this.rate = rate;
        this.movieGoer = movieGoer;
    }
    
    /**
     * This method returns the statement of review
     * @return statement of review
     */
    public String getStatement() {
        return this.statement;
    }

    /**
     * This method set the statement of review
     * @param statement new statement of review
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }
    
    /**
     * This method get the rate of review
     * @return rate of review
     */
    public Integer getRate() {
        return this.rate;
    }

    /**
     * This method set the rate of review
     * @param rate new rate of review
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * This method get the owner of review
     * @return owner of review
     */
    public MovieGoer getMovieGoer() {
        return this.movieGoer;
    }
}
