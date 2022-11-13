package src.Boundary.MovieGoerMenu;

import java.util.Scanner;

import src.Boundary.MainMenu;
import src.Control.MovieGoer_Controller;
import src.Control.Movie_Controller;

public class MovieGoerMainMenu {
    /**
     * Movie Goer menu
     */
    public static void load() {
        System.out.println("-------------Movie Goer------------");
		System.out.println("Choose from one of the following options: \n" +
				"1. List movie \n" + 
                "2. Search movie \n" +
                "3. View Details of Movie \n"  +
                "4. Check seat availability and selection of seats \n" +
				"5. Book and purchase ticket\n" +
                "6. View Booking History \n"+
                "7. List Top 5 ranking by ticket sales OR by overall reviewers' ratings\n" +
                "8. Add Reviews\n" +
                "9. Go Back \n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                Movie_Controller.listAllMovie();
                break;
            case 2:
                Movie_Controller.searchMovie();
                break;
            case 3:
                Movie_Controller.viewMovieDetails();
                break;
            case 4:
                MovieGoer_Controller.showSeatAvailability();
                break;
            case 5:
                MovieGoer_Controller.booking();
                break;
            case 6:
                MovieGoer_Controller.viewHistoryBook();
                break;
            case 7:
                Movie_Controller.listTopFiveMovie();
                break;
            case 8:
                MovieGoer_Controller.addReviews();
                break;
            case 9:
                MainMenu.load();
                break;
            default:
                System.out.println("Invalid choice, please try again");
                MovieGoerMainMenu.load();
                break;
        }
    }

   
    /**
     * Get Search String from UI
     */
    public static String getSearchString(){
        System.out.println("Type in the title of movie you want to search: ");
        Scanner sc = new Scanner(System.in);
        String searchString = sc.next();
        return searchString;
    }

    /**
     * Get choice from UI
     * @param max maximum possible of choice
     */
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

    /**
     * Display String to UI
     */
    public static void displayString(String string){
        System.out.print(string);
    }
}
