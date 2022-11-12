package src.Boundary.AdminMenu;

import java.util.Scanner;

import src.Control.Admin_Controller;

public class AdminSignUp {
	/**
     * Admin Sign Up menu
     */
    public static void load() {
        System.out.println("-----------------Signing Up Account----------");
		Scanner sc = new Scanner(System.in);

		System.out.println("Type In Username:");
		String Username = sc.next();

		System.out.println("Type In Password:");
		String Password = sc.next();

		Admin_Controller.signUp(Username, Password);
	}
}
