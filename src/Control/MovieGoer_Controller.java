package src.Control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import src.Boundary.MainMenu;
import src.Boundary.MovieGoerMenu.MovieGoerMainMenu;
import src.Entity.Booking;
import src.Entity.CONSTANTS;
import src.Entity.Cinema;
import src.Entity.Cineplex;
import src.Entity.Movie;
import src.Entity.MovieGoer;
import src.Entity.ShowTime;
import src.utils.SerializeDB;

public class MovieGoer_Controller {
    public final static String FILENAME = "././data/movieGoer.txt";
    final static int CHANGE_USERNAME = 1;
    final static int CHANGE_PASSWORD = 2;
    private static String username;

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
            MovieGoer_Controller.username = Username;
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

    public static void booking(){
        System.out.println("-----------------Booking-------------");

        ArrayList<Cineplex> cineplexList = Cineplex_Controller.getAllCineplexs();

        Cineplex_Controller.displayShowTimeOfAllCineplex();

        System.out.println("--------------Choose an cineplex-----------");
        int cineplexIndex = MovieGoerMainMenu.getChoice(cineplexList.size())-1;

        Cineplex cineplex = cineplexList.get(cineplexIndex);

        System.out.println("-----------------Booking-------------");

        ArrayList<Cinema> cinemaList = cineplex.getCinema();
        Cineplex_Controller.displayShowTimeOfCineplex(cineplex);

        System.out.println("--------------Choose an cinema-----------");
        int cinemaIndex = MovieGoerMainMenu.getChoice(cinemaList.size()) - 1;

        Cinema cinema = cinemaList.get(cinemaIndex);

        if(cinema.getShowTimeList().size()==0){
            System.out.println("There is no show time to book in this cinema, try again");
            booking();
            return;
        }

        System.out.println("-----------------Booking-------------");
        
        ArrayList<ShowTime>  showTimeList = cinema.getShowTimeList();
        Cineplex_Controller.displayShowTimeOfCinema(cinema);

        System.out.println("--------------Choose an showtime-----------");
        int showTimeIndex = MovieGoerMainMenu.getChoice(showTimeList.size()) - 1;

        ShowTime showTime = showTimeList.get(showTimeIndex);

        showTime.displaySeat();

        System.out.println("[ ]: Normal seat available          [X]: Normal seat taken");

        System.out.println("Type in the column of seat you want:");
        int colIndex = MovieGoerMainMenu.getChoice(showTime.getWidthOfSeat()) - 1;

        System.out.println("Type in the row of seat you want:");
        int rowIndex = MovieGoerMainMenu.getChoice(showTime.getHeightOfSeat()) - 1;

        if(cineplex.bookSeat(cinema.getCinemaCode(), showTimeIndex, colIndex, rowIndex, MovieGoer_Controller.username)){
            ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

            movieGoerList = getAllMovieGoers();

            for(int i=0; i<movieGoerList.size(); i++){
                if(movieGoerList.get(i).getUsername().equals(MovieGoer_Controller.username)){
                    Float price = PriceScheme_Controller.caculatePrice(cinema.getClassOfCinema(), showTime.getMovie().getTypeOfMovie(), movieGoerList.get(i).getAge(), showTime.getShowTime());
                    System.out.println("Your ticket price is " + price);
                    System.out.println("Press enter to confirm booking");
                    Scanner sc = new Scanner(System.in);
                    sc.nextLine();
                    Booking newBooking =  new Booking(colIndex+1, rowIndex+1, price, showTime.getMovie(), cinema, cineplex);
                    movieGoerList.get(i).booking(newBooking);
                    Movie_Controller.increaseRevenue(newBooking.getMovie());
                    System.out.println("Booking Succesfully");
                }
            }

            SerializeDB.writeSerializedObject(FILENAME, movieGoerList);
            
            cineplexList.set(cineplexIndex, cineplex);
            Cineplex_Controller.save(cineplexList);

            MovieGoerMainMenu.load();
        }
    }

    public static void viewHistoryBook(){
        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

        movieGoerList = getAllMovieGoers();

        for(int i=0; i<movieGoerList.size(); i++){
            if(movieGoerList.get(i).getUsername().equals(MovieGoer_Controller.username)){
                MovieGoer movieGoer = movieGoerList.get(i);
                ArrayList<Booking> bookingList = movieGoer.getBookingList();
                for(int j=0; j<bookingList.size(); j++){
                    System.out.println("Title: " + bookingList.get(j).getMovie().getTitle() + "|" +
                    "Seat: " + bookingList.get(j).getSeatColumn() + " " + bookingList.get(j).getSeatRow() + "|" +
                    "Cinema: " + bookingList.get(j).getCinema().getCinemaCode() + "|" +
                    "Cineplex: " + bookingList.get(j).getCineplex().getCineplexName() + "|" +
                    "TransactionId: " + bookingList.get(j).getTransactionId());
                }
            }
        }
        MovieGoerMainMenu.load();
    }

    public static void addReviews(){
        Movie_Controller.addReviews(username);
        MovieGoerMainMenu.load();
    }
}
