package src.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements Serializable {
    private static final long serialVersionUID = 9L;

    /**
     * Transaction Id.
     */
    private String transactionId;

    
    /**
     * Row index of seat.
     */
    private int seatRow;

    /**
     * Column index of seat.
     */
    private int seatColumn;

    /**
     * Price.
     */
    private float price;

    /**
     * Movie.
     */
    private Movie movie;

    /**
     * Cunema.
     */
    private Cinema cinema;

    /**
     * Cineplex.
     */
    private Cineplex cineplex;

    /**
	 * Constructor for Cinema Object
	 * @param seatRow row index of seat
	 * @param seatColumn column index of seat
	 * @param price price of ticket
	 * @param movie movie of booking
     * @param cinema cinema
     * @param cineplex cineplex
	 */
    public Booking(int seatRow, int seatColumn, float price, Movie movie, Cinema cinema, Cineplex cineplex) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.price = price;
        this.movie = movie;
        this.cinema = cinema;
        this.cineplex = cineplex;

        Date date = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMddhhmm");
        this.transactionId = cinema.getCinemaCode() + dt1.format(date);
    }

    /**
	 * Get Seat Row
     * @return Row Index of seat
	 */
    public int getSeatRow() {
        return this.seatRow;
    }

    /**
	 * Get Seat Column
     * @return Column Index of seat
	 */
    public int getSeatColumn() {
        return this.seatColumn;
    }

    /**
	 * Get Price
     * @return Price of Booking
	 */
    public float getPrice() {
        return this.price;
    }

    /**
	 * Get Cinema
     * @return Cinema of Booking
	 */
    public Cinema getCinema() {
        return this.cinema;
    }

    /**
	 * Get Cineplex
     * @return Cineplex of Booking
	 */
    public Cineplex getCineplex() {
        return this.cineplex;
    }

    /**
	 * Get Movie
     * @return Movie of Booking
	 */
    public Movie getMovie() {
        return this.movie;
    }

    /**
	 * Get transactionId
     * @return transactionId of Booking
	 */
    public String getTransactionId() {
        return this.transactionId;
    }
}
