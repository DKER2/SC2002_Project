package src.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 Represents a Booking that have showtime information.
 @author SE4G4
 @version 1.0
 @since 2022-11-09
*/
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

    private ShowTime showTime;

    /**
     * Date Time of Show.
     */
    private Date date;

    /**
	 * Constructor for Cinema Object
	 * @param seatRow row index of seat
	 * @param seatColumn column index of seat
	 * @param price price of ticket
	 * @param movie movie of booking
     * @param cinema cinema
     * @param cineplex cineplex
	 */
    public Booking(int seatRow, int seatColumn, float price, ShowTime showTime) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.price = price;
        this.showTime = showTime;

        Date date = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMddhhmm");
        this.transactionId = showTime.getCinema().getCinemaCode() + dt1.format(date);
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
	 * Get transactionId
     * @return transactionId of Booking
	 */
    public String getTransactionId() {
        return this.transactionId;
    }

     /**
	 * Get Show Time
     * @return Price of Booking
	 */
    public ShowTime getShowTime() {
        return this.showTime;
    }
}
