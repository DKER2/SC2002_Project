package src.Control;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import src.Boundary.MainMenu;
import src.Boundary.AdminMenu.AdminMainMenu;
import src.Entity.Admin;
import src.Entity.Holiday;
import src.utils.SerializeDB;

public class Admin_Controller {
    public final static String FILENAME = "././data/admin.txt";
    final static int CHANGE_USERNAME = 1;
    final static int CHANGE_PASSWORD = 2;

    public Admin_Controller(){

    }

    public static ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        if(SerializeDB.readSerializedObject(FILENAME) == null){
            return adminList;
        }
        adminList = (ArrayList<Admin>) SerializeDB.readSerializedObject(FILENAME);
        return adminList;
    }

    public static boolean addAdmin(String Username, String Password){
        Admin Admin = new Admin(Username, Password);

        ArrayList<Admin> adminList = new ArrayList<Admin>();

        adminList = getAllAdmins();

        boolean exist = false;

        if(adminList != null){
            for(int i=0; i<adminList.size(); i++){
                if(adminList.get(i).getUsername().equals(Username)){
                    exist = true;
                }
            }
        } else{
            adminList = new ArrayList<Admin>();
        }

        if(exist==false){
            adminList.add(Admin);
            SerializeDB.writeSerializedObject(FILENAME, adminList);
        }

        return !exist;
    }

    public static void deleteAdmin(String Username) {
        ArrayList<Admin> Data = new ArrayList<Admin>();

        Data = getAllAdmins();
        
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getUsername() == Username)) {
                UpdateData.add(a);
            }
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }

    public static void updateAdmin(int choice, String Username, String newData) {
        ArrayList<Admin> Data = getAllAdmins();
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getUsername() == Username) {
                switch (choice) {
                    case CHANGE_USERNAME:
                        m.setUsername(newData);
                        break;
                    case CHANGE_PASSWORD:
                        m.setPassword(newData);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }

        SerializeDB.writeSerializedObject(FILENAME, UpdateData);
    }

    public static void signIn(String Username, String Password){
        ArrayList<Admin> Data = new ArrayList<Admin>();

        Data = getAllAdmins();

        boolean exist = false;

        for(int i=0; i<Data.size(); i++){
            if(Data.get(i).getUsername().equals(Username) && Data.get(i).getPassword().equals(Password)){
                exist = true;
            }
        }
        
        if(exist){
			System.out.println("Login Successfuly");
			AdminMainMenu.load();
		}
		else{
			System.out.println("Login Fail");
			MainMenu.load();
		}
    }

    public static void signUp(String Username, String Password){
        boolean status = addAdmin(Username, Password);

		if(status){
			System.out.println("Sign Up Successfuly");
			AdminMainMenu.load();
		}
		else{
			System.out.println("Admin Existed");
            MainMenu.load();
		}
    }

    public static void createUpdateMovie(){
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------Create/Update/Remove Movie-----------");
        System.out.println("Choose from one of the following options: \n" +
                "1. Create \n" + 
                "2. Update \n" +
                "3. Remove \n"+
                "4. Go back\n");
        
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                Movie_Controller.createMovie();
                break;
            case 2:
                Movie_Controller.updateMovie();
                break;
            case 3:
                Movie_Controller.removeMovie();
                break;
        }
    }

    public static void createUpdateShowTime(){
        Cineplex_Controller.displayShowTimeOfAllCineplex();

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------Create/Update/Remove Show Time-----------");
        System.out.println("Choose from one of the following options: \n" +
                "1. Create \n" + 
                "2. Update \n" +
                "3. Remove \n"+
                "4. Go back\n");
        
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                Cineplex_Controller.createShowTime();
                createUpdateShowTime();
                break;
            case 2:
                Cineplex_Controller.updateShowTime();
                break;
            case 3:
                Cineplex_Controller.removeShowTime();
                break;
            case 4:
                AdminMainMenu.load();
                break;
        }
    }

    public static void configSetting(){
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------Configure System---------");
        System.out.println("Choose from one of the following options: \n" +
                "1. Update Ticket Price \n" + 
                "2. Holidays \n" +
                "3. Back \n");
        
        int choice = AdminMainMenu.getChoice(3);

        switch(choice){
            case 1:
                //ticket price update
                break;
            case 2:
                Holiday_Controller.configureHolidays();
                break;
            case 3:
                AdminMainMenu.load();
                break;
        }
    }

    
}
