package src.Boundary.MovieGoerMenu;

import java.util.Scanner;

public class MovieGoerMainMenu {
    public static void load() {
        System.out.println("Admin");
		System.out.println("Choose from one of the following options: \n" +
				"1. Create/Update/Remove Movie Listing \n" + 
				"2. Create/Update/Remove cinema showtimes and movies to be shown \n" +
				"3. Configure system settings \n"+
				"4. Go back\n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                //editMovie();
                break;
            case 2:
                //editCinema();
                break;
            case 3:
                //configSetting();
                break;
            case 4:
                //goBack();
                break;
        }
    }
}
