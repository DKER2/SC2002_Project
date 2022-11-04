package src.Boundary;

import java.util.Scanner;

import src.Boundary.AdminMenu.AdminLogin;
import src.Boundary.AdminMenu.AdminSignUp;
import src.Boundary.MovieGoerMenu.MovieGoerLogin;
import src.Boundary.MovieGoerMenu.MovieGoerSignUp; 

public class MainMenu {
    public static void load() {
		System.out.println("-----------------Welcome to MOBLIMA---------------");
		System.out.println("Please make a selection: \n" +
				  "1. Movie Goer Login \n" +
				  "2. Movie Goer Registration \n" +
				  "3. Admin Login\n" +
				  "4. Admin SignUp\n" +
				  "5. Exit \n");
		
		Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

		switch(choice) {
			case 1:
				MovieGoerLogin.load();
				break;
			case 2:
				MovieGoerSignUp.load();
				break;
			case 3:
                AdminLogin.load();
				break;
			case 4:
				AdminSignUp.load();
				break;
			case 5:
				System.out.println("Exitting application...");
				System.exit(1);
				break;
			default:
				System.out.println("Invalid selection, please try again.");
                load();
		}
	}
}
