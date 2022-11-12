package src.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements Serializable {
    private static final long serialVersionUID = 9L;
    
    private String transactionId;
    private int seatRow;
    private int seatColumn;
    private float price;
    private Movie movie;
    private Cinema cinema;
    private Cineplex cineplex;

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


    public int getSeatRow() {
        return this.seatRow;
    }

    public int getSeatColumn() {
        return this.seatColumn;
    }

    public float getPrice() {
        return this.price;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public Cineplex getCineplex() {
        return this.cineplex;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public String getTransactionId() {
        return this.transactionId;
    }
}
