package src.Entity;

import java.io.Serializable;

public class Booking implements Serializable {
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
    }


    public int getSeatRow() {
        return this.seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return this.seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Cineplex getCineplex() {
        return this.cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
