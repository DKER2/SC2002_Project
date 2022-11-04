package src.Boundary.MovieGoerMenu;

import java.util.Scanner;

import src.Control.MovieGoer_Controller;

public class MovieGoerSignUp {
    public static void load() {
        System.out.println("-------------------Signing Up Account-----------------");
		Scanner sc = new Scanner(System.in);

		System.out.println("Type In Username:");
		String Username = sc.next();

		System.out.println("Type In Password:");
		String Password = sc.next();

		System.out.println("Type In Name:");
		String Name = sc.next();

		System.out.println("Type In Email:");
		String Email = sc.next();

		System.out.println("Type In Phone:");
		String Phone = sc.next();

		System.out.println("Type In Age:");
		Integer Age = sc.nextInt();

		MovieGoer_Controller.signUp(Username, Password, Name, Email, Phone, Age);
	}
}
