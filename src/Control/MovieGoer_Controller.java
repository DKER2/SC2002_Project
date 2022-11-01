package src.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import src.Boundary.MainMenu;
import src.Boundary.MovieGoerMenu.MovieGoerMainMenu;
import src.Entity.Booking;
import src.Entity.CONSTANTS;
import src.Entity.Movie;
import src.Entity.MovieGoer;
import src.Entity.Review;
import src.utils.SerializeDB;

public class MovieGoer_Controller {
    public final static String FILENAME = "././data/movieGoer.txt";
    final static int CHANGE_USERNAME = 1;
    final static int CHANGE_PASSWORD = 2;

    public MovieGoer_Controller(){

    }

    public static ArrayList<MovieGoer> getAllMovieGoers(){
        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return movieGoerList;
        }
        movieGoerList = (ArrayList<MovieGoer>) SerializeDB.readSerializedObject(FILENAME);
        return movieGoerList;
    }

    public static boolean addMovieGoer(String username, String password, String name, String email, String phone, Integer age, ArrayList<Booking> bookingList){
        MovieGoer movieGoer = new MovieGoer(username, password, name, email, phone, age, bookingList);

        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

        movieGoerList = getAllMovieGoers();

        boolean exist = false;

        if(movieGoerList != null){
            for(int i=0; i<movieGoerList.size(); i++){
                if(movieGoerList.get(i).getUsername().equals(username)){
                    exist = true;
                }
            }
        } else{
            movieGoerList = new ArrayList<MovieGoer>();
        }

        if(exist==false){
            movieGoerList.add(movieGoer);
            SerializeDB.writeSerializedObject(FILENAME, movieGoerList);
        }

        return !exist;
    }

    public static void signIn(String Username, String Password){
        ArrayList<MovieGoer> Data = new ArrayList<MovieGoer>();

        Data = getAllMovieGoers();

        boolean exist = false;

        for(int i=0; i<Data.size(); i++){
            if(Data.get(i).getUsername().equals(Username) && Data.get(i).getPassword().equals(Password)){
                exist = true;
            }
        }
        
        if(exist){
			System.out.println("Login Successfuly");
			MovieGoerMainMenu.load();
		}
		else{
			System.out.println("Login Fail");
			MainMenu.load();
		}
    }

    public static void signUp(String username, String password, String name, String email, String phone, Integer age){
        ArrayList<Booking> bookingList = new ArrayList<Booking>();
        boolean status = addMovieGoer(username, password, name, email, phone, age, bookingList);

		if(status){
			System.out.println("Sign Up Successfuly");
			MovieGoerMainMenu.load();
		}
		else{
			System.out.println("MovieGoer Existed");
            MainMenu.load();
		}
    }

    public static void listAllMovie(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        System.out.println();
        System.out.println("Movie list:");
        
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle());
        }

        MovieGoerMainMenu.load();
    }

    public static void searchMovie(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        String searchString = MovieGoerMainMenu.getSearchString();

        ArrayList<Movie> searchResults = new ArrayList<Movie>();

        for(int i=0; i<movieList.size(); i++){
            if(movieList.get(i).getTitle().contains(searchString) && movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.NOWSHOWING)){
                searchResults.add(movieList.get(i));
            }
        }

        if(searchResults.size()==0){
            System.out.println("Do not find a movie with " + searchString);
        }
        else{
            System.out.println("Possible Movie: ");
            for(int i=0; i<searchResults.size(); i++){
                System.out.println((i+1) + "." + searchResults.get(i).getTitle());
            }
            System.out.println((searchResults.size()+1) + "." + "Go Back");

            int choice = MovieGoerMainMenu.getChoice(searchResults.size()+1);

            if(choice==searchResults.size()+1){
                MovieGoerMainMenu.load();
            }
            else{
                Movie movie = searchResults.get(choice);
                System.out.println();
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Synopsis: " + movie.getSynopsis());
                System.out.println("Director: " + movie.getDirector());
                System.out.println("Actors:" );
                for (int i = 0; i < movie.getActorList().size(); i++) {
                    System.out.println(" *" + movie.getActorList().get(i));
                }
                System.out.println("Showing status: " + movie.getShowingStatus());
                System.out.println("Overall rating: " + movie.getOverallRating());
                System.out.println();
            }
        }

        MovieGoerMainMenu.load();
    }

    public static void viewDetails(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        System.out.println();
        System.out.println("Movie list:");
        
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle());
        }

        int choice = MovieGoerMainMenu.getChoice(movieList.size() + 1);

        Movie movie = movieList.get(choice - 1);
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Actors:" );
        for (int i = 0; i < movie.getActorList().size(); i++) {
            System.out.println(" *" + movie.getActorList().get(i));
        }
        System.out.println("Showing status: " + movie.getShowingStatus());
        System.out.println("Overall rating: " + movie.getOverallRating());
        System.out.println();

        MovieGoerMainMenu.load();
    }
   
}
