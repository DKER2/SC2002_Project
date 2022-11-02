package src.Entity;

import java.util.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class ShowTime implements Serializable{
    /**
     * Movie object with information on Movie
     */
    private Movie movie; //object with information on the movie
    /**
     * Cinema object where movie is screened
     */
    private Cinema cinema; //cinema where is the movie showing
    /**
     * Cineplex object where cinema belongs
     */
    private Cineplex cineplex; //cineplex where is the movie showing
    /**
     * Date object for showtime
     */
    private Date showTime; //show time of the movie
    /**
     * Available seats;
     */
    private Seat[][] seats;
    /**
     * Constructor for MovieShowing object
     * @param movie Movie Object
     * @param cinema Cinema where Movie is screened
     * @param cineplex Cineplex where cinema resides in
     * @param date Date object containing information on showtime
     */
    public ShowTime(Movie movie, Cinema cinema, Cineplex cineplex, Date date) { //constructor
        this.movie = movie;
        this.cinema = cinema;
        this.cineplex = cineplex;
        this.showTime = date;
        this.seats = cinema.getSeatLayout();
    }
    /**
     * Generate String with information on Movie Showtime
     * @return String with information on showtime
     */
    public String displayMovieInfo() { //shows the information on the movie. 
        SimpleDateFormat dateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Movie title: " + movie.getTitle() +
               "\nCinema code: " + cinema.getCinemaCode() +
               "\nCinema class: " + cinema.getClassOfCinema() +
               "\nCineplex name: " + cineplex.getCineplexName() +
               "\nMovie Show time: " + dateTime.format(showTime);
    }

    /**
     * Gets Movie for this showtime
     * @return Movie object
     */
    public Movie getMovie() { //returns the information about the movie
        return movie;
    }

    /**
     * Gets information on this showtime
     * @return Date Object containing information of showtime
     */
    public Date getShowTime() { //returns the show time of the movie
        return showTime;
    }
    
    /**
     * Gets Cinema for this showtime
     * @return Cinema object for this showtime
     */
    public Cinema getCinema(){ //returns which cinema the movie is showing
        return cinema;
    }

    /**
     * Sets new showtime for this MovieShowing object
     * @param showTime New Date object with new showtime
     */
    public void setShowTime(Date showTime) { //mutator for attribute showTime
        this.showTime = showTime;
    }
    
    /**
     * Generates String with Cinema Code
     *
     */
    public String toString() {
    	return "Cinema Code: " + cinema.getCinemaCode();
    }

    public Seat[][] getSeats() {
        return this.seats;
    }

    public void setMovie(Movie newMovie) {
        movie = newMovie;
    }
}