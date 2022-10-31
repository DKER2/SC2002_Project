package src.Boundary.AdminMenu;

import java.util.ArrayList;
import java.util.Scanner;

import src.Boundary.MainMenu;
import src.Control.Movie_Controller;
import src.Entity.CONSTANTS;
import src.Entity.Movie;

public class AdminMainMenu {
    public static void load() {
        System.out.println("Admin");
		System.out.println("Choose from one of the following options: \n" +
				"1. Create/Update/Remove Movie \n" + 
				"2. Create/Update/Remove cinema showtimes and movies to be shown \n" +
				"3. Configure system settings \n"+
				"4. Go back\n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                createUpdateMovie();
                break;
            case 2:
                //editCinema();
                break;
            case 3:
                //configSetting();
                break;
            case 4:
                MainMenu.load();
                break;
        }
	}

    private static void createUpdateMovie(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Create/Update/Remove Movie");
        System.out.println("Choose from one of the following options: \n" +
                "1. Create \n" + 
                "2. Update \n" +
                "3. Remove \n"+
                "4. Go back\n");
        
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                addMovie();
                break;
            case 2:
                updateMovie();
                break;
            case 3:
                removeMovie();
                break;
        }
    }

    private static void addMovie(){
        String title = getTitle();
        String sysnopsis = getSynopsis();
        String director = getDirector();
        ArrayList<String> actorList = getActorList();
        CONSTANTS.ShowingStatus showingStatus = getShowingStatus();
        Movie_Controller.addMovie(title, showingStatus, sysnopsis, director, actorList);
    }

    private static void updateMovie() {
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();

        if (movieList == null || movieList.size() == 0) {
            System.out.println("\nThere is no movie to remove!\n");
            return;
        }
        
        System.out.println();
        System.out.println("Movie list:");
        
        int choice = 0;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle());
        }
        System.out.println("Enter your choice:");
        choice = input.nextInt();
        int index = choice - 1;
        Movie movie = movieList.get(index);
        
        System.out.println();
        System.out.println("What would you want to update?");
        System.out.println("1. Title");
        System.out.println("2. Synopsis");
        System.out.println("3. Director");
        System.out.println("4. Actor List");
        System.out.println("5. Showing status");
        System.out.println("Enter your choice:");
        choice = input.nextInt();
        
        Object newData = "";
        switch(choice) {
            case 1:
                newData = getTitle();
                Movie_Controller.updateMovie(choice, movie.getTitle(), newData);
                System.out.println("The movie has been updated!\n");
                break;
            case 2:
                newData = getSynopsis();
                Movie_Controller.updateMovie(choice, movie.getTitle(), newData);
                System.out.println("The movie has been updated!\n");
                break;
            case 3:
                newData = getDirector();
                Movie_Controller.updateMovie(choice, movie.getTitle(), newData);
                System.out.println("The movie has been updated!\n");
                break;
            case 4:
                newData = getActorList();
                Movie_Controller.updateMovie(choice, movie.getTitle(), newData);
                System.out.println("The movie has been updated!\n");
                break;
            case 5:
                newData = getShowingStatus();
                Movie_Controller.updateMovie(choice, movie.getTitle(), newData);
                System.out.println("The movie has been updated!\n");
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public static void removeMovie() {
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();
        if (movieList == null || movieList.size() == 0) {
            System.out.println("\nThere is no movie to remove!\n");
            return;
        }
        
        System.out.println();
        System.out.println("Movie list:");
        
        int choice = 0;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1 ) + ". " + movieList.get(i).getTitle());
        }
        System.out.println("Enter your choice:");
        choice = input.nextInt();
        
        Movie_Controller.deleteMovie(movieList.get(choice-1).getTitle());

        System.out.println("The movie has been removed!\n");
    }

    private static String getTitle(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type In Title:");
        String title = sc.next();

        return title;
    }

    private static String getSynopsis() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type In abstract:");
        String synopsis = sc.nextLine();

        return synopsis;
    }

    private static String getDirector() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type In director:");
        String director = sc.nextLine();

        return director;
    }

    private static ArrayList<String> getActorList() {
        ArrayList<String> cast = new ArrayList<String>();
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter cast size:");
        Integer castSize = input.nextInt();
        input.nextLine();
        for (int i = 0; i < castSize; i++) {
            System.out.println("Enter cast's name " + (i + 1) + ":");
            String castName = input.nextLine();
            cast.add(castName);
        }
        
        return cast;
    }

    private static CONSTANTS.ShowingStatus getShowingStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("==== Please choose showing status ====");
        System.out.println("1. Coming soon");
        System.out.println("2. Preview");
        System.out.println("3. Now showing");
        System.out.println("4. End of showing");
        System.out.println("Enter your choice:");
        CONSTANTS.ShowingStatus showingStatus;
        switch(sc.nextInt()){
            case 1:
                showingStatus = CONSTANTS.ShowingStatus.COMINGSOON;
            case 2:
                showingStatus = CONSTANTS.ShowingStatus.PREVIEW;
            case 3:
                showingStatus = CONSTANTS.ShowingStatus.NOWSHOWING;
            case 4:
                showingStatus = CONSTANTS.ShowingStatus.ENDOFSHOWING;
            default:
                showingStatus = CONSTANTS.ShowingStatus.ENDOFSHOWING;
        }

        return showingStatus;
    } 
}
