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
import src.Entity.Seat;
import src.Entity.ShowTime;
import src.utils.SerializeDB;

public class MovieGoer_Controller {
    /**
     * data file location
     */
    public final static String FILENAME = "././data/movieGoer.txt";
    final static int CHANGE_USERNAME = 1;
    final static int CHANGE_PASSWORD = 2;
    private static String username;

    public MovieGoer_Controller(){

    }

    /**
	 * Get all movie goers
     * @return array list of moviegoer
	 */
    public static ArrayList<MovieGoer> getAllMovieGoers(){
        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return movieGoerList;
        }
        movieGoerList = (ArrayList<MovieGoer>) SerializeDB.readSerializedObject(FILENAME);
        return movieGoerList;
    }

    /**
	 * Add new movie goer
     * @param usernam
     * @param password
     * @param name
     * @param email
     * @param phone
     * @param age
     * @return flag indicate the success of add
	 */
    public static boolean addMovieGoer(String username, String password, String name, String email, String phone, Integer age){
        MovieGoer movieGoer = new MovieGoer(username, password, name, email, phone, age);

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

    /**
	 * Sign In 
     * @param Username
     * @param Password
	 */
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
			MovieGoerMainMenu.displayString("Login Successfuly \n");
            MovieGoer_Controller.username = Username;
			MovieGoerMainMenu.load();
		}
		else{
			MovieGoerMainMenu.displayString("Login Fail\n");
			MainMenu.load();
		}
    }

    /**
	 * Sign Up 
     * @param username
     * @param password
     * @param name
     * @param email
     * @param phone
     * @param age
	 */
    public static void signUp(String username, String password, String name, String email, String phone, Integer age){
        boolean status = addMovieGoer(username, password, name, email, phone, age);

		if(status){
			MovieGoerMainMenu.displayString("Sign Up Successfuly\n");
			MovieGoerMainMenu.load();
		}
		else{
			MovieGoerMainMenu.displayString("MovieGoer Existed\n");
            MainMenu.load();
		}
    }

    /**
	 * Booking method
	 */
    public static void booking(){
        MovieGoerMainMenu.displayString("-----------------Booking-------------\n");

        ArrayList<Cineplex> cineplexList = Cineplex_Controller.getAllCineplexs();

        Cineplex_Controller.displayShowTimeOfAllCineplex();

        MovieGoerMainMenu.displayString("--------------Choose an cineplex-----------\n");
        int cineplexIndex = MovieGoerMainMenu.getChoice(cineplexList.size())-1;

        Cineplex cineplex = cineplexList.get(cineplexIndex);

        MovieGoerMainMenu.displayString("-----------------Booking-------------\n");

        ArrayList<Cinema> cinemaList = cineplex.getCinema();
        Cineplex_Controller.displayShowTimeOfCineplex(cineplex);

        MovieGoerMainMenu.displayString("--------------Choose an cinema-----------\n");
        int cinemaIndex = MovieGoerMainMenu.getChoice(cinemaList.size()) - 1;

        Cinema cinema = cinemaList.get(cinemaIndex);

        if(cinema.getShowTimeList().size()==0){
            MovieGoerMainMenu.displayString("There is no show time to book in this cinema, try again\n");
            booking();
            return;
        }

        MovieGoerMainMenu.displayString("-----------------Booking-------------\n");
        
        ArrayList<ShowTime>  showTimeList = cinema.getShowTimeList();
        Cineplex_Controller.displayShowTimeOfCinema(cinema);

        MovieGoerMainMenu.displayString("--------------Choose an showtime-----------\n");
        int showTimeIndex = MovieGoerMainMenu.getChoice(showTimeList.size()) - 1;

        ShowTime showTime = showTimeList.get(showTimeIndex);

        Seat[][] seats = showTime.getSeats();
        for(int j=0; j<seats[0].length; j++){
            MovieGoerMainMenu.displayString(" "+  (j+1) + " ");
        }
        MovieGoerMainMenu.displayString("\n");

        for(int i=0; i<seats.length; i++){
            MovieGoerMainMenu.displayString((i+1) + " ");
            for(int j=0; j<seats[0].length; j++){
                MovieGoerMainMenu.displayString(seats[i][j].toString());
            }
            MovieGoerMainMenu.displayString("\n");
        }

        MovieGoerMainMenu.displayString("[ ]: Normal seat available          [X]: Normal seat taken\n");

        MovieGoerMainMenu.displayString("Type in the column of seat you want:\n");
        int colIndex = MovieGoerMainMenu.getChoice(showTime.getWidthOfSeat()) - 1;

        MovieGoerMainMenu.displayString("Type in the row of seat you want:\n");
        int rowIndex = MovieGoerMainMenu.getChoice(showTime.getHeightOfSeat()) - 1;

        if(cineplex.bookSeat(cinema.getCinemaCode(), showTimeIndex, colIndex, rowIndex, MovieGoer_Controller.username)){
            ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

            movieGoerList = getAllMovieGoers();

            for(int i=0; i<movieGoerList.size(); i++){
                if(movieGoerList.get(i).getUsername().equals(MovieGoer_Controller.username)){
                    Float price = PriceScheme_Controller.caculatePrice(cinema.getClassOfCinema(), showTime.getMovie().getTypeOfMovie(), movieGoerList.get(i).getAge(), showTime.getShowTime());
                    MovieGoerMainMenu.displayString("Movie: " + showTime.getMovie().getTitle() + "\n");
                    MovieGoerMainMenu.displayString("Showtime: " + showTime.getShowTime() + "\n");
                    MovieGoerMainMenu.displayString("Your ticket price is " + price + "\n");
                    MovieGoerMainMenu.displayString("Press enter to confirm booking \n");
                    Scanner sc = new Scanner(System.in);
                    sc.nextLine();
                    Booking newBooking =  new Booking(colIndex+1, rowIndex+1, price, showTime.getMovie(), cinema, cineplex);
                    movieGoerList.get(i).booking(newBooking);
                    Movie_Controller.increaseRevenue(newBooking.getMovie());
                    MovieGoerMainMenu.displayString("Booking Succesfully\n");
                }
            }

            SerializeDB.writeSerializedObject(FILENAME, movieGoerList);
            
            cineplexList.set(cineplexIndex, cineplex);
            Cineplex_Controller.save(cineplexList);

            MovieGoerMainMenu.load();
        }
        else{
            MovieGoerMainMenu.displayString("The seat is occupied \n \n");
            booking();
        }
    }

    /**
	 * View Booking History of User
	 */
    public static void viewHistoryBook(){
        ArrayList<MovieGoer> movieGoerList = new ArrayList<MovieGoer>();

        movieGoerList = getAllMovieGoers();

        for(int i=0; i<movieGoerList.size(); i++){
            if(movieGoerList.get(i).getUsername().equals(MovieGoer_Controller.username)){
                MovieGoer movieGoer = movieGoerList.get(i);
                ArrayList<Booking> bookingList = movieGoer.getBookingList();
                for(int j=0; j<bookingList.size(); j++){
                    MovieGoerMainMenu.displayString("Title: " + bookingList.get(j).getMovie().getTitle() + "|" +
                    "Seat: " + bookingList.get(j).getSeatColumn() + " " + bookingList.get(j).getSeatRow() + "|" +
                    "Cinema: " + bookingList.get(j).getCinema().getCinemaCode() + "|" +
                    "Cineplex: " + bookingList.get(j).getCineplex().getCineplexName() + "|" +
                    "TransactionId: " + bookingList.get(j).getTransactionId() + "\n");
                }
            }
        }
        MovieGoerMainMenu.load();
    }

    /**
	 * Add Review to a movie
	 */
    public static void addReviews(){
        Movie_Controller.addReviews(username);
        MovieGoerMainMenu.load();
    }
}
