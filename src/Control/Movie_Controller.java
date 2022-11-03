package src.Control;

import java.util.ArrayList;

import src.Entity.CONSTANTS;
import src.Entity.Movie;
import src.Entity.Review;
import src.Boundary.AdminMenu.AdminMainMenu;
import src.Boundary.MovieGoerMenu.MovieGoerMainMenu;
import src.utils.SerializeDB;

public class Movie_Controller {
    public final static String FILENAME = "././data/movie.txt";
    final static int CHANGE_TITLE = 1;
    final static int CHANGE_SYSNOPSIS = 2;
    final static int CHANGE_DIRECTOR = 3;
    final static int CHANGE_ACTOR = 4;
    final static int CHANGE_SHOWINGSTATUS = 5;

    public static ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> MovieList = new ArrayList<Movie>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return MovieList;
        }
        MovieList = (ArrayList<Movie>) SerializeDB.readSerializedObject(FILENAME);
        return MovieList;
    }

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

        System.out.println("The movie has been added!\n");

        AdminMainMenu.load();

        return !exist;
    }

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
    
    public static void updateMovie() {
        ArrayList<Movie> movieList = getAllMovies();

        if (movieList == null || movieList.size() == 0) {
            System.out.println("\nThere is no movie to remove!\n");
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
                System.out.println("Invalid Choice");
                break;
        }

        SerializeDB.writeSerializedObject(FILENAME, movieList);

        System.out.println("The movie has been updated!\n");

        AdminMainMenu.load();
    }

    public static void removeMovie() {
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        int deleteMovieIndex = AdminMainMenu.getMovieIndexFromTerminal();
        
        if (movieList == null || movieList.size() == 0) {
            System.out.println("\nThere is no movie to remove!\n");
            return;
        }
        
        movieList.remove(deleteMovieIndex);

        SerializeDB.writeSerializedObject(FILENAME, movieList);

        System.out.println("The movie has been removed!\n");

        AdminMainMenu.load();
    }

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

    public static void listTopFiveMovie(){

    }
}
