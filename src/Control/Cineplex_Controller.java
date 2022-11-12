package src.Control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import src.Entity.Cinema;
import src.Entity.Cineplex;
import src.Entity.Movie;
import src.Entity.Seat;
import src.Entity.ShowTime;
import src.utils.SerializeDB;
import src.Boundary.AdminMenu.AdminMainMenu;
import src.Entity.CONSTANTS;

public class Cineplex_Controller {
    /**
     * data file location
     */
    public final static String FILENAME = "././data/cineplex.txt";

    public Cineplex_Controller(){

    }
    
    /**
	 * Get all cineplex 
     * @return array list of cineplex
	 */
    public static ArrayList<Cineplex> getAllCineplexs(){
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return cineplexList;
        }
        cineplexList = (ArrayList<Cineplex>) SerializeDB.readSerializedObject(FILENAME);
        return cineplexList;
    }

    /**
	 * Add cineplex 
     * @param cineplexName cineplex name 
     * @param cinemaList list of cinema
	 */
    public static boolean addCineplex(String cineplexName,ArrayList<Cinema> cinemasList){
        Cineplex cineplex = new Cineplex(cineplexName, cinemasList);

        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();

        cineplexList = getAllCineplexs();

        boolean exist = false;

        if(cineplexList != null){
            for(int i=0; i<cineplexList.size(); i++){
                if(cineplexList.get(i).getCineplexName().equals(cineplexName)){
                    exist = true;
                }
            }
        } else{
            cineplexList = new ArrayList<Cineplex>();
        }

        if(exist==false){
            cineplexList.add(cineplex);
            AdminMainMenu.displayString("Added Cineplex Sucessfully\n");
            SerializeDB.writeSerializedObject(FILENAME, cineplexList);
        }

        return !exist;
    }

    /**
	 * Save new cineplexList
     * @param cineplexList new list of Cineplex
	 */
    public static void save(ArrayList<Cineplex> cineplexList){
        SerializeDB.writeSerializedObject(FILENAME, cineplexList);
    }

    /**
	 * Display all showtime of all cineplex
	 */
    public static void displayShowTimeOfAllCineplex(){
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(int i=0; i<cineplexList.size(); i++){
            Cineplex tmp = cineplexList.get(i);
            AdminMainMenu.displayString((i+1) + "." + tmp.getCineplexName() + "\n");
            displayShowTimeOfCineplex(tmp);
        }
    }

    /**
	 * Display all showtime of a cineplex
	 */
    public static void displayShowTimeOfCineplex(Cineplex cineplex){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ArrayList<Cinema> cinemaList = cineplex.getCinema();
        for(int j=0; j<cinemaList.size(); j++){
            Cinema cinema = cinemaList.get(j);
            AdminMainMenu.displayString((j+1) + "." + cinema.getCinemaCode() + "\n");
            displayShowTimeOfCinema(cinema);
        }
    }

    /**
	 * Display all showtime of a cinema
	 */
    public static void displayShowTimeOfCinema(Cinema cinema){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ArrayList<ShowTime> showTimeList = cinema.getShowTimeList();

        Collections.sort(showTimeList, new Comparator<ShowTime>() {
            public int compare(ShowTime o1, ShowTime o2) {
                return o1.getShowTime().compareTo(o2.getShowTime());
            }
        });

        AdminMainMenu.displayString("----------------------------\n");
        for(int k=0; k<showTimeList.size(); k++){
            AdminMainMenu.displayString((k+1) + "." + dateFormat.format(showTimeList.get(k).getShowTime()) + "\n");
            if(dateFormat.format(showTimeList.get(k).getShowTime()).length() <= 40){
                for(int u=0; u<40-dateFormat.format(showTimeList.get(k).getShowTime()).length(); u++){
                    AdminMainMenu.displayString(" ");
                }
            }
            AdminMainMenu.displayString("|");
        }

        System.out.print("\n");

        for(int k=0; k<showTimeList.size(); k++){
            AdminMainMenu.displayString(showTimeList.get(k).getMovie().getTitle() + "\n");
            if(showTimeList.get(k).getMovie().getTitle().toString().length() <= 40+2){
                for(int u=0; u<40-showTimeList.get(k).getMovie().getTitle().toString().length()+2; u++){
                    AdminMainMenu.displayString(" ");
                }
            }
            AdminMainMenu.displayString("|");
        }
        AdminMainMenu.displayString("\n");
    }

    /**
	 * Create new showtime
	 */
    public static void createShowTime(){
        displayShowTimeOfAllCineplex();
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        AdminMainMenu.displayString("Choose an cineplex to create show time \n");
        int cineplexIndex = AdminMainMenu.getChoice(cineplexList.size());

        Cineplex cineplex  = cineplexList.get(cineplexIndex-1);

        displayShowTimeOfCineplex(cineplex);

        AdminMainMenu.displayString("Choose an cinema to create show time \n");
        int cinemaIndex = AdminMainMenu.getChoice(cineplex.getCinema().size());

        Cinema cinema = cineplex.getCinema().get(cinemaIndex-1);

        displayShowTimeOfCinema(cinema);

        int movieIndex = AdminMainMenu.getMovieIndexFromTerminal();
        Movie movie = Movie_Controller.getAllShowingMovies().get(movieIndex);
        AdminMainMenu.displayString(movie.getTitle() + "\n");
        
        AdminMainMenu.displayString("Type in Date \n");
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        Date time = new Date();
        try{
            time = formatter1.parse(AdminMainMenu.getString());
        }catch(Exception ex){
            AdminMainMenu.displayString("Please Follow Datetime format dd/MM/yyyy HH:mm \n");
            createShowTime();
        }


        ShowTime newShowTime = new ShowTime(movie, cinema, cineplex, time);

        cineplex.addShowTime(cinema.getCinemaCode(), newShowTime);

        cineplexList.set(cineplexIndex-1, cineplex);
        SerializeDB.writeSerializedObject(FILENAME, cineplexList);
    }

    /**
	 * Update showtime
	 */
    public static void updateShowTime(){
        displayShowTimeOfAllCineplex();
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        AdminMainMenu.displayString("Choose an cineplex to change \n");
        int cineplexIndex = AdminMainMenu.getChoice(cineplexList.size())-1;

        Cineplex cineplex  = cineplexList.get(cineplexIndex);

        displayShowTimeOfCineplex(cineplex);

        AdminMainMenu.displayString("Choose an cinema to change \n");
        int cinemaIndex = AdminMainMenu.getChoice(cineplex.getCinema().size())-1;

        Cinema cinema = cineplex.getCinema().get(cinemaIndex);

        
        if(cinema.getShowTimeList().size()==0){
            AdminMainMenu.displayString("There is no show time to update in this cinema\n");
            updateShowTime();
            return;
        }
        
        displayShowTimeOfCinema(cinema);

        AdminMainMenu.displayString("Choose an showtime to change");
        int showTimeIndex = AdminMainMenu.getChoice(cinema.getShowTimeList().size())-1;

        ShowTime showTime = cinema.getShowTimeList().get(showTimeIndex);

        AdminMainMenu.displayString("---------Choose following option to change--------\n"
            +"1. Movie\n"+
            "2. Date \n"+
            "3. Go back\n");
        int choice = AdminMainMenu.getChoice(3);

        switch(choice){
            case 1:
                AdminMainMenu.displayString("Choose a movie to subtitue into time slot\n");
                int movieIndex = AdminMainMenu.getMovieIndexFromTerminal();
                showTime.setMovie(Movie_Controller.getAllMovies().get(movieIndex));
                cineplex.updateShowTime(cinema.getCinemaCode(), showTime, showTimeIndex);
                cineplexList.set(cineplexIndex, cineplex);
                SerializeDB.writeSerializedObject(FILENAME, cineplexList);
                AdminMainMenu.load();
                break;
            case 2:
                AdminMainMenu.displayString("Type in Date\n");
                SimpleDateFormat formatter1=new SimpleDateFormat("dd/mm/yyyy");  
                Date time = new Date();
                try{
                    time = formatter1.parse(AdminMainMenu.getString());
                }catch(Exception ex){
                    AdminMainMenu.displayString("Please Follow Datetime format dd/MM/YYYY\n");
                    createShowTime();
                }
                showTime.setShowTime(time);
                cineplex.updateShowTime(cinema.getCinemaCode(), showTime, showTimeIndex);
                cineplexList.set(cineplexIndex, cineplex);
                SerializeDB.writeSerializedObject(FILENAME, cineplexList);
                AdminMainMenu.load();
                break;
            case 3:
                Cineplex_Controller.createUpdateShowTime();
                break;
            default:
                Cineplex_Controller.createUpdateShowTime();
                break;
        }
    }

    /**
	 * Remove showtime
	 */
    public static void removeShowTime(){
        displayShowTimeOfAllCineplex();
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        AdminMainMenu.displayString("Choose an cineplex to remove an showtime\n");
        int cineplexIndex = AdminMainMenu.getChoice(cineplexList.size())-1;

        Cineplex cineplex  = cineplexList.get(cineplexIndex);

        displayShowTimeOfCineplex(cineplex);

        AdminMainMenu.displayString("Choose an cinema to remove an showtime\n");
        int cinemaIndex = AdminMainMenu.getChoice(cineplex.getCinema().size())-1;

        Cinema cinema = cineplex.getCinema().get(cinemaIndex);

        if(cinema.getShowTimeList().size()==0){
            AdminMainMenu.displayString("There is no show time to remove in this cinema, try again\n");
            removeShowTime();
            return;
        }

        displayShowTimeOfCinema(cinema);

        AdminMainMenu.displayString("Choose an showtime to remove");
        int showTimeIndex = AdminMainMenu.getChoice(cinema.getShowTimeList().size())-1;

        cineplex.removeShowTime(cinema.getCinemaCode(), showTimeIndex);

        cineplexList.set(cineplexIndex, cineplex);
        SerializeDB.writeSerializedObject(FILENAME, cineplexList);

        AdminMainMenu.load();
    }

    /**
	 * Create/Update/Remove showtime
	 */
    public static void createUpdateShowTime(){
        Cineplex_Controller.displayShowTimeOfAllCineplex();

        Scanner sc = new Scanner(System.in);
        AdminMainMenu.displayString("-----------Create/Update/Remove Show Time-----------\n");
        AdminMainMenu.displayString("Choose from one of the following options: \n" +
                "1. Create \n" + 
                "2. Update \n" +
                "3. Remove \n"+
                "4. Go back\n");
        
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                Cineplex_Controller.createShowTime();
                createUpdateShowTime();
                break;
            case 2:
                Cineplex_Controller.updateShowTime();
                break;
            case 3:
                Cineplex_Controller.removeShowTime();
                break;
            case 4:
                AdminMainMenu.load();
                break;
        }
    }

    public static void main(String args[]){
        Seat[] seats = new Seat[144];
        for(int i=0; i<144; i++){
            seats[i] = new Seat(CONSTANTS.seatStatus.NOTTAKEN, "");
        }
        //ArrayList<ShowTime> showTimeList = new ArrayList<ShowTime>();
        ArrayList<Cinema> cathyCinema = new ArrayList<Cinema>();
        cathyCinema.add(new Cinema(CONSTANTS.ClassOfCinema.GOLD, "JEM", 12, 12, seats));
        cathyCinema.add(new Cinema(CONSTANTS.ClassOfCinema.MAX, "WES", 12, 12, seats));
        cathyCinema.add(new Cinema(CONSTANTS.ClassOfCinema.NORMAL, "AMK", 12, 12, seats));
        addCineplex("Jurong Point Cineplex", cathyCinema);
        ArrayList<Cinema> goldenVillage = new ArrayList<Cinema>();
        goldenVillage.add(new Cinema(CONSTANTS.ClassOfCinema.GOLD, "VIV", 12, 12, seats));
        goldenVillage.add(new Cinema(CONSTANTS.ClassOfCinema.MAX, "CIT", 12, 12, seats));
        goldenVillage.add(new Cinema(CONSTANTS.ClassOfCinema.NORMAL, "JUR", 12, 12, seats));
        addCineplex("Vivo City Cineplex", goldenVillage);
    }
}
