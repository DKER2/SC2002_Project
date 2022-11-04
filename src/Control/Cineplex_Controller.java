package src.Control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import src.Entity.Cinema;
import src.Entity.Cineplex;
import src.Entity.Movie;
import src.Entity.Seat;
import src.Entity.ShowTime;
import src.utils.SerializeDB;
import src.Boundary.AdminMenu.AdminMainMenu;
import src.Entity.CONSTANTS;

public class Cineplex_Controller {
    public final static String FILENAME = "././data/cineplex.txt";

    public Cineplex_Controller(){

    }

    public static ArrayList<Cineplex> getAllCineplexs(){
        ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return cineplexList;
        }
        cineplexList = (ArrayList<Cineplex>) SerializeDB.readSerializedObject(FILENAME);
        return cineplexList;
    }

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
            System.out.println("Added Cineplex Sucessfully");
            SerializeDB.writeSerializedObject(FILENAME, cineplexList);
        }

        return !exist;
    }

    public static void save(ArrayList<Cineplex> cineplexList){
        SerializeDB.writeSerializedObject(FILENAME, cineplexList);
    }

    public static void displayShowTimeOfAllCineplex(){
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(int i=0; i<cineplexList.size(); i++){
            Cineplex tmp = cineplexList.get(i);
            System.out.print((i+1) + "." + tmp.getCineplexName() + "\n");
            displayShowTimeOfCineplex(tmp);
        }
    }

    public static void displayShowTimeOfCineplex(Cineplex cineplex){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ArrayList<Cinema> cinemaList = cineplex.getCinema();
        for(int j=0; j<cinemaList.size(); j++){
            Cinema cinema = cinemaList.get(j);
            System.out.print((j+1) + "." + cinema.getCinemaCode() + "\n");
            displayShowTimeOfCinema(cinema);
        }
    }

    public static void displayShowTimeOfCinema(Cinema cinema){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ArrayList<ShowTime> showTimeList = cinema.getShowTimeList();

        Collections.sort(showTimeList, new Comparator<ShowTime>() {
            public int compare(ShowTime o1, ShowTime o2) {
                return o1.getShowTime().compareTo(o2.getShowTime());
            }
        });

        System.out.println("----------------------------");
        for(int k=0; k<showTimeList.size(); k++){
            System.out.print((k+1) + "." + dateFormat.format(showTimeList.get(k).getShowTime()));
            if(dateFormat.format(showTimeList.get(k).getShowTime()).length() <= 40){
                for(int u=0; u<40-dateFormat.format(showTimeList.get(k).getShowTime()).length(); u++){
                    System.out.print(" ");
                }
            }
            System.out.print("|");
        }

        System.out.print("\n");

        for(int k=0; k<showTimeList.size(); k++){
            System.out.print(showTimeList.get(k).getMovie().getTitle());
            if(showTimeList.get(k).getMovie().getTitle().toString().length() <= 40+2){
                for(int u=0; u<40-showTimeList.get(k).getMovie().getTitle().toString().length()+2; u++){
                    System.out.print(" ");
                }
            }
            System.out.print("|");
        }
        System.out.print("\n");
    }

    public static void createShowTime(){
        displayShowTimeOfAllCineplex();
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        System.out.println("Choose an cineplex to create show time");
        int cineplexIndex = AdminMainMenu.getChoice(cineplexList.size());

        Cineplex cineplex  = cineplexList.get(cineplexIndex-1);

        displayShowTimeOfCineplex(cineplex);

        System.out.println("Choose an cinema to create show time");
        int cinemaIndex = AdminMainMenu.getChoice(cineplex.getCinema().size());

        Cinema cinema = cineplex.getCinema().get(cinemaIndex-1);

        displayShowTimeOfCinema(cinema);

        int movieIndex = AdminMainMenu.getMovieIndexFromTerminal();
        Movie movie = Movie_Controller.getAllMovies().get(movieIndex);
        
        System.out.println("Type in Date");
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        Date time = new Date();
        try{
            time = formatter1.parse(AdminMainMenu.getString());
        }catch(Exception ex){
            System.out.println("Please Follow Datetime format dd/MM/yyyy HH:mm");
            createShowTime();
        }


        ShowTime newShowTime = new ShowTime(movie, cinema, cineplex, time);

        cineplex.addShowTime(cinema.getCinemaCode(), newShowTime);

        cineplexList.set(cineplexIndex-1, cineplex);
        SerializeDB.writeSerializedObject(FILENAME, cineplexList);
    }

    public static void updateShowTime(){
        displayShowTimeOfAllCineplex();
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        System.out.println("Choose an cineplex to change");
        int cineplexIndex = AdminMainMenu.getChoice(cineplexList.size())-1;

        Cineplex cineplex  = cineplexList.get(cineplexIndex);

        displayShowTimeOfCineplex(cineplex);

        System.out.println("Choose an cinema to change");
        int cinemaIndex = AdminMainMenu.getChoice(cineplex.getCinema().size())-1;

        Cinema cinema = cineplex.getCinema().get(cinemaIndex);

        displayShowTimeOfCinema(cinema);

        if(cinema.getShowTimeList().size()==0){
            System.out.println("There is no show time to update in this cinema");
            updateShowTime();
            return;
        }

        System.out.println("Choose an showtime to change");
        int showTimeIndex = AdminMainMenu.getChoice(cinema.getShowTimeList().size())-1;

        ShowTime showTime = cinema.getShowTimeList().get(showTimeIndex);

        System.out.println("---------Choose following option to change--------\n"
            +"1. Movie\n"+
            "2. Date \n"+
            "3. Go back");
        int choice = AdminMainMenu.getChoice(3);

        switch(choice){
            case 1:
                System.out.println("Choose a movie to subtitue into time slot");
                int movieIndex = AdminMainMenu.getMovieIndexFromTerminal();
                showTime.setMovie(Movie_Controller.getAllMovies().get(movieIndex));
                cineplex.updateShowTime(cinema.getCinemaCode(), showTime, showTimeIndex);
                cineplexList.set(cineplexIndex, cineplex);
                SerializeDB.writeSerializedObject(FILENAME, cineplexList);
                AdminMainMenu.load();
                break;
            case 2:
                System.out.println("Type in Date");
                SimpleDateFormat formatter1=new SimpleDateFormat("dd/mm/yyyy");  
                Date time = new Date();
                try{
                    time = formatter1.parse(AdminMainMenu.getString());
                }catch(Exception ex){
                    System.out.println("Please Follow Datetime format dd/MM/YYYY");
                    createShowTime();
                }
                showTime.setShowTime(time);
                cineplex.updateShowTime(cinema.getCinemaCode(), showTime, showTimeIndex);
                cineplexList.set(cineplexIndex, cineplex);
                SerializeDB.writeSerializedObject(FILENAME, cineplexList);
                AdminMainMenu.load();
                break;
            case 3:
                Admin_Controller.createUpdateShowTime();
                break;
            default:
                Admin_Controller.createUpdateShowTime();
                break;
        }
    }

    public static void removeShowTime(){
        displayShowTimeOfAllCineplex();
        ArrayList<Cineplex> cineplexList = getAllCineplexs();
        System.out.println("Choose an cineplex to remove an showtime");
        int cineplexIndex = AdminMainMenu.getChoice(cineplexList.size())-1;

        Cineplex cineplex  = cineplexList.get(cineplexIndex);

        displayShowTimeOfCineplex(cineplex);

        System.out.println("Choose an cinema to remove an showtime");
        int cinemaIndex = AdminMainMenu.getChoice(cineplex.getCinema().size())-1;

        Cinema cinema = cineplex.getCinema().get(cinemaIndex);

        if(cinema.getShowTimeList().size()==0){
            System.out.println("There is no show time to remove in this cinema, try again");
            removeShowTime();
            return;
        }

        displayShowTimeOfCinema(cinema);

        System.out.println("Choose an showtime to remove");
        int showTimeIndex = AdminMainMenu.getChoice(cinema.getShowTimeList().size())-1;

        cineplex.removeShowTime(cinema.getCinemaCode(), showTimeIndex);

        cineplexList.set(cineplexIndex, cineplex);
        SerializeDB.writeSerializedObject(FILENAME, cineplexList);

        AdminMainMenu.load();
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
