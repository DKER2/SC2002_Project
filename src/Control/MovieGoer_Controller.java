package src.Control;

import java.util.ArrayList;
import java.util.HashMap;

import src.Boundary.MainMenu;
import src.Boundary.MovieGoerMenu.MovieGoerMainMenu;
import src.Entity.Booking;
import src.Entity.Movie;
import src.Entity.MovieGoer;
import src.Entity.Review;
import src.utils.SerializeDB;

public class MovieGoer_Controller {
    public final static String FILENAME = "././data/MovieGoer.txt";
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

    public static boolean addMovieGoer(String username, String password, String name, String email, String phone, Integer age, HashMap<Movie,Review> postedReviewsList, ArrayList<Booking> bookingList){
        MovieGoer movieGoer = new MovieGoer(username, password, name, email, phone, age, postedReviewsList, bookingList);

        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

        movieGoerList = getAllMovieGoers();

        boolean exist = false;

        if(movieGoerList != null){
            for(int i=0; i<movieGoerList.size(); i++){
                if(movieGoerList.get(i).getUsername() == username){
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

    public static void deleteMovieGoer(String Username) {
        ArrayList<MovieGoer> Data = new ArrayList<MovieGoer>();

        Data = getAllMovieGoers();
        
        ArrayList<MovieGoer> UpdateData = new ArrayList<MovieGoer>();
        MovieGoer a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getUsername() == Username)) {
                UpdateData.add(a);
            }
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }

    public static void updateMovieGoer(int choice, String Username, String newData) {
        ArrayList<MovieGoer> Data = getAllMovieGoers();
        ArrayList<MovieGoer> UpdateData = new ArrayList<MovieGoer>();
        MovieGoer m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getUsername() == Username) {
                switch (choice) {
                    case CHANGE_USERNAME:
                        m.setUsername(newData);
                        break;
                    case CHANGE_PASSWORD:
                        m.setPassword(newData);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
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
        HashMap<Movie,Review> postedReviewsList = new HashMap<Movie,Review>();
        ArrayList<Booking> bookingList = new ArrayList<Booking>();
        boolean status = addMovieGoer(username, password, name, email, phone, age, postedReviewsList, bookingList);

		if(status){
			System.out.println("Sign Up Successfuly");
			MovieGoerMainMenu.load();
		}
		else{
			System.out.println("MovieGoer Existed");
            MainMenu.load();
		}
    }
}
