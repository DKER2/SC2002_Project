package src.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import src.Entity.Booking;
import src.Entity.CONSTANTS;
import src.Entity.Movie;
import src.Entity.MovieGoer;
import src.Entity.Review;
import src.Boundary.AdminMenu.AdminMainMenu;
import src.Boundary.MovieGoerMenu.MovieGoerMainMenu;
import src.utils.SerializeDB;

public class Movie_Controller {
    /**
     * data file location
     */
    public final static String FILENAME = "././data/movie.txt";
    final static int CHANGE_TITLE = 1;
    final static int CHANGE_SYSNOPSIS = 2;
    final static int CHANGE_DIRECTOR = 3;
    final static int CHANGE_ACTOR = 4;
    final static int CHANGE_SHOWINGSTATUS = 5;

    /**
     * Get all movies
     * @return Array list of movie
     */
    public static ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> MovieList = new ArrayList<Movie>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return MovieList;
        }
        MovieList = (ArrayList<Movie>) SerializeDB.readSerializedObject(FILENAME);
        return MovieList;
    }

    /**
     * Get all showing movies
     * @return Array list of showing movie
     */
    public static ArrayList<Movie> getAllShowingMovies(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();
        ArrayList<Movie> showingMovieList = new ArrayList<Movie>();

        int choice = 0;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.NOWSHOWING)){
                showingMovieList.add(movieList.get(i));
            }
        }

        return showingMovieList;
    }

    /**
     * Add movies
     * @param titile
     * @param showingStatus
     * @param censorship
     * @param typeOfMovie
     * @param sysnopsis
     * @param director
     * @param actorList
     * @return flag indicate the success 
     */
    public static boolean addMovie(String title, CONSTANTS.ShowingStatus showingStatus, CONSTANTS.Censorship censorship, CONSTANTS.TypeOfMovie typeOfMovie, String sysnopsis, String director, ArrayList<String> actorList){
        ArrayList<Review> review_list = new ArrayList<Review>();
        Movie Movie = new Movie(title, showingStatus, censorship, typeOfMovie, sysnopsis, director, actorList, 0, review_list);

        ArrayList<Movie> MovieList = new ArrayList<Movie>();

        MovieList = getAllMovies();

        boolean exist = false;

        if(MovieList != null){
            for(int i=0; i<MovieList.size(); i++){
                if(MovieList.get(i).getTitle().equals(title)){
                    exist = true;
                }
            }
        } else{
            MovieList = new ArrayList<Movie>();
        }

        if(exist==false){
            MovieList.add(Movie);
            SerializeDB.writeSerializedObject(FILENAME, MovieList);
        }

        AdminMainMenu.displayString("The movie has been added!\n");

        AdminMainMenu.load();

        return !exist;
    }

    /**
	 * Save new movieList
     * @param movieList new list of Movie
	 */
    public static void save(ArrayList<Movie> movieList){
        SerializeDB.writeSerializedObject(FILENAME, movieList);
    }

    /**
	 * Create movie
	 */
    public static void createMovie(){
        String title = AdminMainMenu.getTitleFromTerminal();
        String sysnopsis = AdminMainMenu.getSynopsisFromTerminal();
        String director = AdminMainMenu.getDirectorFromTerminal();
        ArrayList<String> actorList = AdminMainMenu.getActorListFromTerminal();
        CONSTANTS.ShowingStatus showingStatus = AdminMainMenu.getShowingStatusFromTerminal();
        CONSTANTS.Censorship censorship = AdminMainMenu.getCensorshipFromTerminal();
        CONSTANTS.TypeOfMovie typeOfMovie = AdminMainMenu.getTypeOfMovieFromTerminal();
        Movie_Controller.addMovie(title, showingStatus, censorship, typeOfMovie, sysnopsis, director, actorList);
    }
    
    /**
	 * Update movie
	 */
    public static void updateMovie() {
        ArrayList<Movie> movieList = getAllMovies();

        if (movieList == null || movieList.size() == 0) {
            AdminMainMenu.displayString("\nThere is no movie to remove!\n");
            return;
        }
        
        int changIndexMovie = AdminMainMenu.getMovieIndexFromTerminal();
        
        int choice = AdminMainMenu.getUpdateOptionFromTerminal();
        
        Object newData = "";
        switch(choice) {
            case 1:
                newData = AdminMainMenu.getTitleFromTerminal();
                movieList.get(changIndexMovie).setTitle((String) newData);
                break;
            case 2:
                newData = AdminMainMenu.getSynopsisFromTerminal();
                movieList.get(changIndexMovie).setSynopsis((String) newData);
                break;
            case 3:
                newData = AdminMainMenu.getDirectorFromTerminal();
                movieList.get(changIndexMovie).setDirector((String) newData);
                break;
            case 4:
                newData = AdminMainMenu.getActorListFromTerminal();
                movieList.get(changIndexMovie).setActorList((ArrayList<String>) newData);
                break;
            case 5:
                newData = AdminMainMenu.getShowingStatusFromTerminal();
                movieList.get(changIndexMovie).setShowingStatus((CONSTANTS.ShowingStatus) newData);
                break;
            case 6:
                newData = AdminMainMenu.getTypeOfMovieFromTerminal();
                movieList.get(changIndexMovie).setTypeOfMovie((CONSTANTS.TypeOfMovie) newData);
                break; 
            default:
                AdminMainMenu.displayString("Invalid Choice\n");
                break;
        }

        SerializeDB.writeSerializedObject(FILENAME, movieList);

        AdminMainMenu.displayString("The movie has been updated!\n");

        AdminMainMenu.load();
    }

    /**
	 * Remove movie
	 */
    public static void removeMovie() {
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        int deleteMovieIndex = AdminMainMenu.getMovieIndexFromTerminal();
        
        if (movieList == null || movieList.size() == 0) {
            AdminMainMenu.displayString("\nThere is no movie to remove!\n");
            return;
        }
        
        movieList.remove(deleteMovieIndex);

        SerializeDB.writeSerializedObject(FILENAME, movieList);

        AdminMainMenu.displayString("The movie has been removed!\n");

        AdminMainMenu.load();
    }

    /**
	 * Increase the revenue of movie
	 */
    public static void increaseRevenue(Movie movie){
        ArrayList<Movie> MovieList = new ArrayList<Movie>();

        MovieList = getAllMovies();

        boolean exist = false;

        if(MovieList != null){
            for(int i=0; i<MovieList.size(); i++){
                if(MovieList.get(i).getTitle().equals(movie.getTitle())){
                    Movie newData = MovieList.get(i);
                    newData.increaseRevenue(1);
                    MovieList.set(i, newData);
                }
            }
        } else{
            MovieList = new ArrayList<Movie>();
        }

        SerializeDB.writeSerializedObject(FILENAME, MovieList);
    }

    /**
	 * Add Review
	 */
    public static void addReviews(String username){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();
        Scanner sc = new Scanner(System.in);

        MovieGoerMainMenu.displayString("Movie list:\n");
        
        for (int i = 0; i < movieList.size(); i++) {
            MovieGoerMainMenu.displayString((i + 1 ) + ". " + movieList.get(i).getTitle() + "\n");
        }
        
        int movieIndex = MovieGoerMainMenu.getChoice(movieList.size()) - 1;
        
        Movie movie = movieList.get(movieIndex); 

        MovieGoerMainMenu.displayString("Type in review statement \n");
        String statement = sc.nextLine();

        MovieGoerMainMenu.displayString("Type in your rating [1-5] \n");
        int rating = sc.nextInt();

        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

        movieGoerList = MovieGoer_Controller.getAllMovieGoers();
        for(int i=0; i<movieGoerList.size(); i++){
            if(movieGoerList.get(i).getUsername().equals(username)){
                MovieGoer movieGoer = movieGoerList.get(i);
                Review newReview = new Review(statement, rating, movieGoer);
                movie.addReview(newReview);
            }
        }
        
        movieList.set(movieIndex, movie);
        SerializeDB.writeSerializedObject(FILENAME, movieList);
    }

    /**
	 * View Movie Details
	 */
    public static void viewMovieDetails(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        MovieGoerMainMenu.displayString("Movie list:\n");
        
        ArrayList<Movie> showingMovieList = new ArrayList<Movie>();

        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.NOWSHOWING)
            || movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.COMINGSOON)){
                showingMovieList.add(movieList.get(i));
            }
        }

        for (int i = 0; i < showingMovieList.size(); i++) {
            MovieGoerMainMenu.displayString((i+1) + "." + showingMovieList.get(i).getTitle() + "\n");
        }

        int choice = MovieGoerMainMenu.getChoice(showingMovieList.size() + 1);

        Movie movie = showingMovieList.get(choice - 1);

        MovieGoerMainMenu.displayString("Title: " + movie.getTitle() + "\n");
        MovieGoerMainMenu.displayString("Type of movie: " + movie.getTypeOfMovie() + "\n");
        MovieGoerMainMenu.displayString("Synopsis: " + movie.getSynopsis() + "\n");
        MovieGoerMainMenu.displayString("Director: " + movie.getDirector() + "\n");
        MovieGoerMainMenu.displayString("Actors:" + "\n");
        for (int i = 0; i < movie.getActorList().size(); i++) {
            MovieGoerMainMenu.displayString(" *" + movie.getActorList().get(i) +"\n");
        }
        MovieGoerMainMenu.displayString("Review: " + "\n");
        for (int i = 0; i < movie.getReview_list().size(); i++) {
            MovieGoerMainMenu.displayString(" *" + movie.getReview_list().get(i).getStatement() + " from " + movie.getReview_list().get(i).getMovieGoer().getName() +"\n");
        }
        MovieGoerMainMenu.displayString("Showing status: " + movie.getShowingStatus() + "\n");
        MovieGoerMainMenu.displayString("Overall rating: " + movie.getOverallRating() + "\n");
        MovieGoerMainMenu.displayString("Rating: " + movie.getCensorship().name() + "\n");

        MovieGoerMainMenu.load();
    }

    /**
	 * List all movie
	 */
    public static void listAllMovie(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        MovieGoerMainMenu.displayString("--------------Movie list------------\n");
        
        ArrayList<Movie> showingMovieList = new ArrayList<Movie>();
        
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.NOWSHOWING) || movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.COMINGSOON)){
                showingMovieList.add(movieList.get(i));
            }
        }

        for (int i = 0; i < showingMovieList.size(); i++) {
            MovieGoerMainMenu.displayString((i+1) + "." + showingMovieList.get(i).getTitle() + "\n");
        }

        MovieGoerMainMenu.load();
    }

    /**
	 * Search movie
	 */
    public static void searchMovie(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        String searchString = MovieGoerMainMenu.getSearchString();

        ArrayList<Movie> searchResults = new ArrayList<Movie>();

        for(int i=0; i<movieList.size(); i++){
            if(movieList.get(i).getTitle().toLowerCase().contains(searchString.toLowerCase()) && movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.NOWSHOWING) ||
            movieList.get(i).getTitle().toLowerCase().contains(searchString.toLowerCase()) && movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.COMINGSOON)){
                searchResults.add(movieList.get(i));
            }
        }

        if(searchResults.size()==0){
            MovieGoerMainMenu.displayString("Do not find a movie with " + searchString + "\n");
        }
        else{
            MovieGoerMainMenu.displayString("--------------------Possible Movie--------------- \n");
            for(int i=0; i<searchResults.size(); i++){
                MovieGoerMainMenu.displayString((i+1) + "." + searchResults.get(i).getTitle() + "\n");
            }
            MovieGoerMainMenu.displayString((searchResults.size()+1) + "." + "Go Back" + "\n");

            MovieGoerMainMenu.displayString("Please choose an Movie to view details, otherwise choose last number to go back \n");
            int choice = MovieGoerMainMenu.getChoice(searchResults.size()+1);

            if(choice==searchResults.size()+1){
                MovieGoerMainMenu.load();
            }
            else{
                MovieGoerMainMenu.displayString("-----------Details----------- \n");
                Movie movie = searchResults.get(choice-1);
                MovieGoerMainMenu.displayString("Title: " + movie.getTitle() + "\n");
                MovieGoerMainMenu.displayString("Type of movie: " + movie.getTypeOfMovie() + "\n");
                MovieGoerMainMenu.displayString("Synopsis: " + movie.getSynopsis() + "\n");
                MovieGoerMainMenu.displayString("Director: " + movie.getDirector() + "\n");
                MovieGoerMainMenu.displayString("Actors: \n" );
                for (int i = 0; i < movie.getActorList().size(); i++) {
                    MovieGoerMainMenu.displayString(" *" + movie.getActorList().get(i) + "\n");
                }
                MovieGoerMainMenu.displayString("Showing status: " + movie.getShowingStatus() +"\n");
                MovieGoerMainMenu.displayString("Overall rating: " + movie.getOverallRating() + "\n");
                MovieGoerMainMenu.displayString("Rating: " + movie.getCensorship().name() + "\n");
            }
        }

        MovieGoerMainMenu.load();
    }

    /**
	 * List top five movie
	 */
    public static void listTopFiveMovie(){
        MovieGoerMainMenu.displayString("Top Five Movie According to \n");
        MovieGoerMainMenu.displayString("1. Review \n" +
        "2. Sale \n" +
        "3. Go Back \n");

        int choice = MovieGoerMainMenu.getChoice(3);

        switch(choice){
            case 1:
                listTopFiveMovieByReview();
                MovieGoerMainMenu.load();
                break;
            case 2:
                listTopFiveMovieBySale();
                MovieGoerMainMenu.load();
                break;
            case 3:
                MovieGoerMainMenu.load();
                break;
        }
        
    }

    /**
	 * List top five movie by review
	 */
    public static void listTopFiveMovieByReview(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        Collections.sort(movieList, new Comparator<Movie>() {
            public int compare(Movie o1, Movie o2) {
                return o2.getOverallRating().compareTo(o1.getOverallRating());
            }
        });

        MovieGoerMainMenu.displayString("----------Top 5 Movie By Review----------- \n");
        for(int i=0; i<5 && i<movieList.size(); i++){
            MovieGoerMainMenu.displayString((i+1) + "." + movieList.get(i).getTitle() + "\n");
        }
    }

    /**
	 * List top five movie by sale
	 */
    public static void listTopFiveMovieBySale(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        Collections.sort(movieList, new Comparator<Movie>() {
            public int compare(Movie o1, Movie o2) {
                return o2.getRevenue().compareTo(o1.getRevenue());
            }
        });

        MovieGoerMainMenu.displayString("----------Top 5 Movie By Sale-----------\n");
        for(int i=0; i<5 && i<movieList.size(); i++){
            MovieGoerMainMenu.displayString((i+1) + "." + movieList.get(i).getTitle() + "|" + " Total Sale: " + movieList.get(i).getRevenue()+"\n");
        }
    }

    /**
	 * Create/Update/Remove Movie
	 */
    public static void createUpdateMovie(){
        Scanner sc = new Scanner(System.in);
        AdminMainMenu.displayString("-----------Create/Update/Remove Movie-----------\n");
        AdminMainMenu.displayString("Choose from one of the following options: \n" +
                "1. Create \n" + 
                "2. Update \n" +
                "3. Remove \n"+
                "4. Go back\n");
        
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                Movie_Controller.createMovie();
                break;
            case 2:
                Movie_Controller.updateMovie();
                break;
            case 3:
                Movie_Controller.removeMovie();
                break;
        }
    }
}
