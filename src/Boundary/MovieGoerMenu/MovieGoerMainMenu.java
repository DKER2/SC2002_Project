package src.Boundary.MovieGoerMenu;

import java.util.Scanner;

import src.Control.MovieGoer_Controller;

public class MovieGoerMainMenu {
    public static void load() {
        System.out.println("Movie Goer");
		System.out.println("Choose from one of the following options: \n" +
				"1. List movie \n" + 
                "2. Search movie \n" +
                "3. View Details of Moive \n"  +
				"4. Book and purchase ticket\n" +
                "5. View Booking History \n"+
                "6. List Top 5 ranking by ticket sales OR by overall reviewers' ratings\n" +
                "7. Go Back \n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                MovieGoer_Controller.listAllMovie();
                break;
            case 2:
                MovieGoer_Controller.searchMovie();
                break;
            case 3:
                MovieGoer_Controller.viewDetails();
                break;
            case 4:
                MovieGoer_Controller.booking();
                break;
            case 5:
                MovieGoer_Controller.viewHistoryBook();
                break;
            case 6:
                MovieGoer_Controller.listTopFiveMovie();
                break;
            default:
                System.out.println("Invalid choice, please try again");
                MovieGoerMainMenu.load();
                break;
        }
    }

   

    public static String getSearchString(){
        System.out.println("Type in the title of movie you want to search: ");
        Scanner sc = new Scanner(System.in);
        String searchString = sc.next();
        return searchString;
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
}
