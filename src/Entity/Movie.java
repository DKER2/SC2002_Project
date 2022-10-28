package src.Entity;

import java.util.ArrayList;

/**
 * This class contains all the information of a Movie
 */

public class Movie {
    /**
	* The title of the movie
	*/
    private String movieTitle;

    /**
	* The showing status of the movie
	*/
    private CONSTANTS.ShowingStatus showingStatus;

    /**
	* The abstract SYNOPSIS of the movie
	*/
    private String synopsis;

    /**
	* The director of the movie
	*/
    private String director;

    /**
	* The list of name of actors of the movie
	*/
    private ArrayList<String> actorList;

    /**
	* The list of review of the movie
	*/
    private ArrayList<Review> review_list;

    /**
     * Constructor for the Movie class.
     * 
     * @param movieTitle         title of the movie
     * @param showingStatus   Showing status of the movie
     * @param synopsis abstract of the movie
     * @param director  director who worked on the movie
     * @param actorList     list of actors in the movie
     * @param reviewList    list of reviews and ratings that the movie have
     */
    public Movie(String movieTitle, CONSTANTS.ShowingStatus showingStatus, 
    String sysnopsis, String director, ArrayList<String> actorList,
    ArrayList<Review> review_list){
        this.movieTitle = movieTitle;;
        this.showingStatus = showingStatus;
        this.synopsis = sysnopsis;
        this.director = director;
        this.actorList = actorList;
        this.review_list = review_list;
    }

}
