package src.Boundary.MovieGoerMenu;

import java.util.Scanner;

import src.Control.MovieGoer_Controller;

public class MovieGoerLogin {
	/**
     * Movie Goer Login Menu
     */
    public static void load() {
		System.out.println("-----------Logging In Account------------");
		Scanner sc = new Scanner(System.in);

		System.out.println("Type In Username:");
		String Username = sc.next();

		System.out.println("Type In Password:");
		String Password = sc.next();

		MovieGoer_Controller.signIn(Username, Password);
	}
}
