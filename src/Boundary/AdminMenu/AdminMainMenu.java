package src.Boundary.AdminMenu;

import java.util.ArrayList;
import java.util.Scanner;

import src.Boundary.MainMenu;
import src.Control.Admin_Controller;
import src.Control.Movie_Controller;
import src.Entity.CONSTANTS;
import src.Entity.Movie;

public class AdminMainMenu {
    public static void load() {
        System.out.println("------------Admin----------");
		System.out.println("Choose from one of the following options: \n" +
				"1. Create/Update/Remove Movie \n" + 
				"2. Create/Update/Remove cinema showtimes and movies to be shown \n" +
				"3. Configure system settings \n"+
				"4. Go back\n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                Admin_Controller.createUpdateMovie();
                break;
            case 2:
                Admin_Controller.createUpdateShowTime();
                break;
            case 3:
                Admin_Controller.configSetting();
                break;
            case 4:
                MainMenu.load();
                break;
            default: 
                System.out.println("Invalid choice, please try again");
                AdminMainMenu.load();
                break;
        }
	}

    
    
    public static int getUpdateOptionFromTerminal(){
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("What would you want to update?");
        System.out.println("1. Title");
        System.out.println("2. Synopsis");
        System.out.println("3. Director");
        System.out.println("4. Actor List");
        System.out.println("5. Showing status");
        System.out.println("Enter your choice:");
        int choice = input.nextInt();

        return choice;
    }

    public static int getMovieIndexFromTerminal(){
        ArrayList<Movie> movieList = Movie_Controller.getAllMovies();
        ArrayList<Movie> showingMovieList = new ArrayList<Movie>();

        System.out.println();
        System.out.println("Movie list:");
        
        int choice = 0;
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getShowingStatus().equals(CONSTANTS.ShowingStatus.NOWSHOWING)){
                showingMovieList.add(movieList.get(i));
            }
        }

        for (int i = 0; i < showingMovieList.size(); i++) {
            System.out.println((i+1) + "." + showingMovieList.get(i).getTitle());
        }

        System.out.println("Enter your choice:");
        choice = input.nextInt();
        int index = choice - 1;
        if(index >= showingMovieList.size()){
            System.out.println("Invalid Index, please try again");
            return getMovieIndexFromTerminal();
        }

        return index;
    }

    public static String getTitleFromTerminal(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type In Title:");
        String title = sc.nextLine();

        return title;
    }

    public static String getSynopsisFromTerminal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type In abstract:");
        String synopsis = sc.nextLine();

        return synopsis;
    }

    public static String getDirectorFromTerminal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type In director:");
        String director = sc.nextLine();

        return director;
    }

    public static ArrayList<String> getActorListFromTerminal() {
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

    public static CONSTANTS.ShowingStatus getShowingStatusFromTerminal(){
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
                break;
            case 2:
                showingStatus = CONSTANTS.ShowingStatus.PREVIEW;
                break;
            case 3:
                showingStatus = CONSTANTS.ShowingStatus.NOWSHOWING;
                break;
            case 4:
                showingStatus = CONSTANTS.ShowingStatus.ENDOFSHOWING;
                break;
            default:
                showingStatus = CONSTANTS.ShowingStatus.ENDOFSHOWING;
                break;
        }

        return showingStatus;
    } 

    public static CONSTANTS.Censorship getCensorshipFromTerminal(){
        Scanner sc = new Scanner(System.in);
        System.out.println("==== Please choose Censorship status ====");
        System.out.println("1. G");
        System.out.println("2. PG");
        System.out.println("3. PG13");
        System.out.println("4. NC16");
        System.out.println("5. M18");
        System.out.println("6. R21");
        System.out.println("Enter your choice:");
        CONSTANTS.Censorship censorStatus;
        switch(sc.nextInt()){
            case 1:
                censorStatus = CONSTANTS.Censorship.G;
                break;
            case 2:
                censorStatus = CONSTANTS.Censorship.PG;
                break;
            case 3:
                censorStatus = CONSTANTS.Censorship.PG13;
                break;
            case 4:
                censorStatus = CONSTANTS.Censorship.NC16;
                break;
            case 5:
                censorStatus = CONSTANTS.Censorship.M18;
                break;
            case 6:
                censorStatus = CONSTANTS.Censorship.R21;
                break;
            default:
                censorStatus = CONSTANTS.Censorship.G;
                break;
        }

        return censorStatus;
    } 

    public static CONSTANTS.TypeOfMovie getTypeOfMovieFromTerminal(){
        Scanner sc = new Scanner(System.in);
        System.out.println("==== Please choose type of movie ====");
        System.out.println("1. DIGITAL");
        System.out.println("2. BLOCKBUSTER");
        System.out.println("3. 3D");
        System.out.println("Enter your choice:");
        CONSTANTS.TypeOfMovie censorStatus;
        switch(sc.nextInt()){
            case 1:
                censorStatus = CONSTANTS.TypeOfMovie.DIGITAL;
                break;
            case 2:
                censorStatus = CONSTANTS.TypeOfMovie.BLOCKBUSTER;
                break;
            case 3:
                censorStatus = CONSTANTS.TypeOfMovie._3D;
                break;
            default:
                censorStatus = CONSTANTS.TypeOfMovie.DIGITAL;
                break;
        }

        return censorStatus;
    } 

    public static int getChoice(int max){
        System.out.println("Type your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice<=0 || choice>max){
            System.out.println("Invalid choice, please try again ");
            return getChoice(max);
        }
        return choice;
    }

    public static String getString(){
        Scanner sc = new Scanner(System.in);
        String searchString = sc.nextLine();
        return searchString;
    }
}
