package src.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the information of a Movie
 */

public class Movie implements Serializable{
    /**
	* The title of the movie
	*/
    private String title;

    /**
	* The showing status of the movie
	*/
    private CONSTANTS.ShowingStatus showingStatus;

    /**
	* The censorship of the movie
	*/
    private CONSTANTS.Censorship censorship;

    /**
     * Type of Movie for this Movie
     */
    private CONSTANTS.TypeOfMovie typeOfMovie;

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
     * Revenue from sales for this Movie
     */
    private int revenue;

    /**
	* The list of review of the movie
	*/
    private ArrayList<Review> review_list;

    /**
     * Constructor for the Movie class.
     * 
     * @param title         title of the movie
     * @param showingStatus   Showing status of the movie
     * @param censorship Showing censorship of the movie
     * @param synopsis abstract of the movie
     * @param director  director who worked on the movie
     * @param actorList     list of actors in the movie
     * @param reviewList    list of reviews and ratings that the movie have
     * @param revenue
     */

    public Movie(String title, CONSTANTS.ShowingStatus showingStatus, CONSTANTS.Censorship censorship, CONSTANTS.TypeOfMovie typeOfMovie, String synopsis, String director, ArrayList<String> actorList, int revenue, ArrayList<Review> review_list) {
        this.title = title;
        this.showingStatus = showingStatus;
        this.censorship = censorship;
        this.typeOfMovie = typeOfMovie;
        this.synopsis = synopsis;
        this.director = director;
        this.actorList = actorList;
        this.revenue = revenue;
        this.review_list = review_list;
    }
    

    /**
     * This method returns the title of the movie
     * @return the title of the movie
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This method sets a new title for the movie
     * @param title is the new title for the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method returns the abstract of the movie
     * @return the abstract of the movie
     */
    public String getSynopsis() {
        return this.synopsis;
    }

    /**
     * This method sets a new abstract for the movie
     * @param synopsis is the new abstract for the movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * This method returns the showing status of the movie
     * @return the showing status of the movie
     */
    public CONSTANTS.ShowingStatus getShowingStatus() {
        return this.showingStatus;
    }
    
    /**
     * This method sets a new showing status for the movie
     * @param showingStatus is the new showing status for the movie
     */
    public void setShowingStatus(CONSTANTS.ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }


    /**
     * This method returns the director of the movie
     * @return the director of the movie
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * This method sets a new director for the movie
     * @param director is the new director for the movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * This method returns the actors of the movie
     * @return the actors of the movie
     */
    public ArrayList<String> getActorList() {
        return this.actorList;
    }

    /**
     * This method sets a new actorList for the movie
     * @param actorList is the new actorList for the movie
     */
    public void setActorList(ArrayList<String> actorList) {
        this.actorList = actorList;
    }

    /**
     * Adds a new actor to the actor list
     * @param actor is the new actor to be added.
     */
    public void addActor(String actor) {
        for (int i = 0; i < this.actorList.size(); i++) {
            if (this.actorList.get(i).toLowerCase() == actor.toLowerCase()) {
                System.out.println("This actor already exist in the list. Can not add the same actor");
                return;
            }
        }
        this.actorList.add(actor);
    }

    /**
     * This method removes an actor from the actorList
     * @param actor is the actor to remove.
     */
    public void removeActor(String actor) {
        int index = 0;
        for (int i = 0; i < this.actorList.size(); i++) {
            if (this.actorList.get(i).toLowerCase() == actor.toLowerCase()) {
                this.actorList.remove(index);
                return;
            }
        }
        System.out.println("This actor does not exist in the list.");
    }

    /**
     * This method returns the reviews of the movie
     * @return the reviews of the movie
     */
    public ArrayList<Review> getReview_list() {
        return this.review_list;
    }

    public float getOverallRating(){
        ArrayList<Review> reviewList = getReview_list();
        float averageScore = (float) 0.0;

        for(int i=0; i<reviewList.size(); i++){
            averageScore += reviewList.get(i).getRate();
        }

        return averageScore/reviewList.size();
    }

    public void increaseRevenue(int increaseAmount){
        revenue = revenue + increaseAmount;
    }

    public CONSTANTS.Censorship getCensorship() {
        return this.censorship;
    }

    public void setCensorship(CONSTANTS.Censorship censorship) {
        this.censorship = censorship;
    }

    public void setReview_list(ArrayList<Review> review_list) {
        this.review_list = review_list;
    }

    public CONSTANTS.TypeOfMovie getTypeOfMovie() {
        return this.typeOfMovie;
    }

    public void setTypeOfMovie(CONSTANTS.TypeOfMovie typeOfMovie) {
        this.typeOfMovie = typeOfMovie;
    }

}
